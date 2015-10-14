package com.jquantium.bean.core;

import com.jquantium.bean.Url;
import com.jquantium.bean.localization.Language;
import com.jquantium.bean.security.UserSecurity;
import com.jquantium.helper.UserHelper;
import com.jquantium.service.CORE;
import com.jquantium.service.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mykhailo_Bohdanov on 03/07/2015.
 */
public class Context {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Url url;

    protected Language language;

    protected UserSecurity user;

    private long START_MILLIS       = System.currentTimeMillis();
    private long START_NANO         = System.nanoTime();

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

    public Language getLanguage() {
        return language;
    }
    public String getWord(String key) {
        if (language != null) {
            return language.getWord(key);
        }

        return null;
    }

    public boolean setLanguage() {
        String code = null;
        Object attr;

        if (request != null) {
            if ((code = request.getParameter("language")) == null || !CORE.localization.hasLanguage(code)) {
                if (user == null || (code = user.getLanguageCode()) == null || !CORE.localization.hasLanguage(code)) {
                    if (request.getSession() == null || (attr = request.getSession().getAttribute("language")) == null || "null".equals(code = String.valueOf(attr)) || CORE.localization.hasLanguage(code)) {
                        if (request.getLocale() == null || (code = request.getLocale().getLanguage()) == null || CORE.localization.hasLanguage(code)) {
                            code = null;
                        }
                    }
                }
            }
        }

        if (code == null || (language = CORE.localization.getLanguage(code)) == null || !language.isEnabled()) {
            code = CORE.config.getString("CORE", "defaultLanguage");//TODO
        }

        language    = CORE.localization.getLanguage(code);

        if (language == null) {
            return false;
        }

        return true;
    }
    public boolean setLanguage(String code) {
        if (!CORE.localization.hasLanguage(code)) {
            return false;
        }

        if (request.getSession() != null) {
            request.getSession().setAttribute("language", code);
        }

        if (user != null) {
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
