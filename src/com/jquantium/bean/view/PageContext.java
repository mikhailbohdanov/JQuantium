package com.jquantium.bean.view;

import com.jquantium.bean.Url;
import com.jquantium.bean.core.Context;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class PageContext extends Context {
    public static PageContext newInstance(HttpServletRequest request, Model model) {
        return newInstance(request, null, model, null);
    }
    public static PageContext newInstance(HttpServletRequest request, HttpServletResponse response, Model model) {
        return newInstance(request, response, model, null);
    }
    public static PageContext newInstance(HttpServletRequest request, Model model, Url url) {
        return newInstance(request, null, model, url);
    }
    public static PageContext newInstance(HttpServletRequest request, HttpServletResponse response, Model model, Url url) {
        PageContext PC  = new PageContext(request, response, model);

        PC.setLanguage();

        if (url == null)
            PC.setUrl(new Url(request));
        else
            PC.setUrl(url);

        return PC;
    }




    private String module;
    private String action;
    private Model model;

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

    public String render() {
        return "";
    }

}
