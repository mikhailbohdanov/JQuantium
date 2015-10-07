package com.jquantium.bean.view;

import com.jquantium.bean.Url;
import com.jquantium.bean.core.Context;
import com.jquantium.bean.localization.Language;
import com.jquantium.bean.user.User;
import com.jquantium.service.CORE;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class PageContext extends Context {
    private String module;
    private String action;
    private Model model;

    private User user;
    private Language language;

    protected PageContext() {
        super();
    }

    public PageContext(HttpServletRequest request, HttpServletResponse response, Model model) {
        super(request, response);

        this.model = model;
    }


    public PageContext setView(String module, String action) {
        this.module = module;
        this.action = action;

        return this;
    }
    public PageContext setModule(String module) {
        this.module = module;

        return this;
    }
    public PageContext setAction(String action) {
        this.action = action;

        return this;
    }

    public PageContext setLanguage() {
        String code = null;
        Object attr;

        if (request != null) {
            if ((code = request.getParameter("language")) == null || !CORE.localization.hasLanguage(code)) {
                if (user == null || (code = user.getLanguageCode()) == null || !CORE.localization.hasLanguage(code)) {
                    if (request.getSession() == null || (attr = request.getSession().getAttribute("language")) == null || (code = String.valueOf(attr)).equals(null) || CORE.localization.hasLanguage(code)) {
                        if (request.getLocale() == null || (code = request.getLocale().getLanguage()) == null || CORE.localization.hasLanguage(code)) {
                            code = null;
                        }
                    }
                }
            }
        }

        if (code == null || (language = CORE.localization.getLanguage(code)) == null || !language.isEnabled())
            code = CORE.config.getString("CORE", "defaultLanguage");

        language    = CORE.localization.getLanguage(code);

        return this;
    }





    public String render() {
        return "";
    }

}
