/*
 * Canadian Tire Corporation, Ltd. Do not reproduce without permission in writing. Copyright (c) 2015 Canadian Tire
 * Corporation, Ltd. All rights reserved.
 */
package com.jquantium.util.auto.annotation;

import com.ctc.mobile.models.exceptions.DssResponseException;
import com.ctc.mobile.models.helpers.StatusModel;
import com.ctc.mobile.models.security.HybrisToken;
import com.ctc.mobile.utils.MobileProxyConstants;
import com.ctc.mobile.utils.MobileUtils;
import com.ctc.mobile.utils.SessionUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ctc.mobile.utils.MobileProxyConstants.ErrorCodes.*;
import static com.ctc.mobile.utils.MobileProxyConstants.ErrorMessages.*;
import static com.ctc.mobile.utils.MobileProxyConstants.Exceptions.BAD_CREDENTIALS_EXCEPTION;
import static com.ctc.mobile.utils.MobileProxyConstants.Exceptions.LOGGED_IN_WITH_OTHER;
import static com.ctc.mobile.utils.MobileProxyConstants.*;
import static com.ctc.mobile.utils.MobileProxyConstants.Exceptions.LINE_LIMIT_REACHED_EXCEPTION;

@Aspect
@Component
public class StatusProcessor {

    private static final String TIMEOUT_EXCEPTION = "SocketTimeoutException";
    private static final String ACCOUNT_LOCKED_EXCEPTION = "User account is locked";

    private final Logger LOG = Logger.getLogger(StatusProcessor.class);

    private final HttpStatus OK_HTTP_STATUS = HttpStatus.OK;
    private final HttpStatus UNAUTHORIZED_HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    @Around(value = "execution(public * com.ctc.mobile.controllers..*Controller.*(..)) && @annotation(processing))")
    public ResponseEntity<String> processPerforming(ProceedingJoinPoint joinPoint, StatusProcessing processing) {

        boolean userLoggedIn = getUserLoggedIn(joinPoint);
        try {
            @SuppressWarnings("unchecked")
            ResponseEntity<String> result = (ResponseEntity) joinPoint.proceed();
            if (processing.returnHeaders()) {

                return setCookieToResult(joinPoint, result);
            }

            return result;

        } catch (DssResponseException exception) {

            if (!processing.wrapExceptions()) {

                return new ResponseEntity<>(exception.getMessage(), null, exception.getStatus());
            }

            return createErrorResponse(exception.getPrefix() + exception.getMessage(), userLoggedIn);
        } catch (Throwable throwable) {
            LOG.error("Error joinpoint process: ", throwable);

            return createErrorResponse(throwable.getMessage(), userLoggedIn);
        }
    }

    private ResponseEntity<String> createErrorResponse(String message, Boolean loggedIn) {
        try {
            StatusModel error = new StatusModel(MobileProxyConstants.Status.ERROR, message);

            if (message.contains(TIMEOUT_EXCEPTION)) {

                error.setErrorCode(TIMEOUT_ERROR_CODE);
            } else if (message.contains(BAD_CREDENTIALS_EXCEPTION)) {
                StatusModel unauthorized = new StatusModel(Status.FAIL,
                        BAD_CRIDENTIALS_MESSAGE, BAD_CRIDENTIALS);

                return new ResponseEntity<>(MobileUtils.toJson(unauthorized), UNAUTHORIZED_HTTP_STATUS);
            } else if (message.contains(ACCOUNT_LOCKED_EXCEPTION)) {

                StatusModel unauthorized = new StatusModel(Status.FAIL,
                        ACCOUNT_LOCKED_MESSAGE, ACCOUNT_LOCKED);

                return new ResponseEntity<>(MobileUtils.toJson(unauthorized), loggedIn ?
                        OK_HTTP_STATUS :
                        UNAUTHORIZED_HTTP_STATUS);
            } else if (message.contains(LINE_LIMIT_REACHED_EXCEPTION)) {

                StatusModel lineLimit = new StatusModel(Status.FAIL,
                        LINE_LIMIT_REACHED_MESSAGE, LINE_LIMIT_REACHED);

                return new ResponseEntity<>(MobileUtils.toJson(lineLimit), OK_HTTP_STATUS);
            } else if (message.contains(STORE_IS_NOT_SET_EXCEPTION)) {

                return new ResponseEntity<>(MobileUtils.toJson(
                        new StatusModel(Status.FAIL, STORE_IS_NOT_SET_MESSAGE)), OK_HTTP_STATUS);
            } else if (message.contains(LOGGED_IN_WITH_OTHER)) {
                StatusModel alreadyLoggedIn = new StatusModel(Status.FAIL,
                        USER_ALREADY_LOGED_IN, BAD_CRIDENTIALS);

                return new ResponseEntity<>(MobileUtils.toJson(alreadyLoggedIn), UNAUTHORIZED_HTTP_STATUS);
            }

            return new ResponseEntity<>(MobileUtils.toJson(error), OK_HTTP_STATUS);
        } catch (IOException e) {
            LOG.error("Error creating error response: " + message);

            return new ResponseEntity<>("Error creating error response:" + e.getMessage(), OK_HTTP_STATUS);
        }
    }

    private ResponseEntity<String> setCookieToResult(ProceedingJoinPoint joinPoint, ResponseEntity<String> result) {
        for (Object argument:  joinPoint.getArgs()) {
            if (argument instanceof HttpServletRequest) {
                try {
                    HttpSession session = ((HttpServletRequest) argument).getSession();
                    List<String> cookies = cookiesToReturn(session);
                    HttpHeaders headers = new HttpHeaders();
                    headers.put(SET_COOKIE_HEADER, cookies);

                    return new ResponseEntity<>(result.getBody(), headers, result.getStatusCode());
                } catch(Exception e) {
                    LOG.error("Error setting cookie to result: " + e);

                    return result;
                }
            }
        }

        return result;
    }

    private List<String> cookiesToReturn(HttpSession session) {
        List<String> cookies = new ArrayList<>();

        addCookieToList(cookies, SessionUtils.getSessionAttribute(session, HYBRIS_CART_ID));
        addCookieToList(cookies, SessionUtils.getSessionAttribute(session, HYBRIS_JSESSION_ID));

        return cookies;
    }

    private void addCookieToList(List<String> cookies, String cookie) {
        if (!cookie.isEmpty()) {
            cookies.add(cookie);
        }
    }

    private boolean getUserLoggedIn(ProceedingJoinPoint joinPoint) {
        try {
            HttpSession session = getRequest(joinPoint).getSession();

            if (!SessionUtils.isAnonymous(session)) {
                HybrisToken token = SessionUtils.getHybrisAuthToken(session);

                return token.getStatus() == HybrisToken.LoginStatus.COMPLETED;
            }

        } catch(Exception e) {
            LOG.error("Error checking user is logged in: " + e);
        }

        return false;
    }

    private HttpServletRequest getRequest(ProceedingJoinPoint joinPoint) {
        for (Object argument : joinPoint.getArgs()) {
            if (argument instanceof HttpServletRequest) {
                return (HttpServletRequest) argument;
            }
        }
        return null;
    }
}