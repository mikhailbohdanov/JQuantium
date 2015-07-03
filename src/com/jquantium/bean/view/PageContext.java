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
    private String module;
    private String action;
    private Model model;

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

    public String render() {
        return "";
    }

}
