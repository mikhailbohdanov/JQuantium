package com.jquantium.bean.core;

import com.jquantium.bean.Url;
import com.jquantium.bean.localization.Language;
import com.jquantium.bean.security.UserSecurity;
import com.jquantium.helper.UserHelper;
import com.jquantium.service.CORE;
import com.jquantium.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jquantium.util.Assert.EMPTY;
import static com.jquantium.util.Assert.EQUALS;
import static com.jquantium.util.Assert.NULL;

/**
 * Created by Mykhailo_Bohdanov on 03/07/2015.
 */
public class Context {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Url url;

    protected Language language;

    protected UserSecurity user;

    private long START_MILLIS = System.currentTimeMillis();
    private long START_NANO = System.nanoTime();

    protected Context() {
        this.user = UserHelper.getMe();


    }

    public Context(HttpServletRequest request, HttpServletResponse response) {
        this();

        this.request = request;
        this.response = response;
    }

    public long getDurationMillis() {
        return System.currentTimeMillis() - START_MILLIS;
    }

    public long getDurationNano() {
        return System.nanoTime() - START_NANO;
    }

    public long getDuration() {
        return Long.parseLong(String.valueOf(getDurationMillis()) + String.valueOf(getDurationNano()));
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Url getUrl() {
        return url;
    }

    public Context setUrl(Url url) {
        this.url = url;

        return this;
    }

    public Context setUrl(String url) {
        this.url = new Url(url);

        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public String getWord(String key) {
        if (NULL(language) || EMPTY(key)) {
            return null;
        }

        return language.getWord(key);
    }

    public boolean setLanguage() {
        String code = null;
        Object attr;

        if (!NULL(request)) {
            if (EMPTY(code = request.getParameter("language")) ||
                    !CORE.localization.hasLanguage(code)) {
                if (NULL(user) ||
                        EMPTY(code = user.getLanguageCode()) ||
                        !CORE.localization.hasLanguage(code)) {
                    if (NULL(request.getSession()) ||
                            NULL(attr = request.getSession().getAttribute("language")) ||
                            EQUALS(code = String.valueOf(attr), "null") ||
                            CORE.localization.hasLanguage(code)) {
                        if (NULL(request.getLocale()) ||
                                EMPTY(code = request.getLocale().getLanguage()) ||
                                CORE.localization.hasLanguage(code)) {
                            code = null;
                        }
                    }
                }
            }
        }

        if (EMPTY(code) ||
                NULL(language = CORE.localization.getLanguage(code)) ||
                !language.isEnabled()) {
            code = CORE.config.getString("CORE", "defaultLanguage");//TODO
        }

        language = CORE.localization.getLanguage(code);

        if (NULL(language)) {
            return false;
        }

        return true;
    }

    public boolean setLanguage(String code) {
        if (!CORE.localization.hasLanguage(code)) {
            return false;
        }

        if (!NULL(request.getSession())) {
            request.getSession().setAttribute("language", code);
        }

        if (!NULL(user)) {
            user.setLanguageCode(code);
//TODO            MainServices.securityService.updateSecurityUser(user);
        }

        return true;
    }

    public UserSecurity getUser() {
        return user;
    }

    public Context setUser(UserSecurity user) {
        this.user = user;

        return this;
    }

}
