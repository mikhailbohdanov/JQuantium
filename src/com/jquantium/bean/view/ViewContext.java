package com.jquantium.bean.view;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mykhailo_Bohdanov on 09/02/2016.
 */
public class ViewContext extends PageContext {
    private Model model;
    private String module;
    private String action;

    protected ViewContext() {
        super();
    }

    public ViewContext(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public Model getModel() {
        return model;
    }

    public ViewContext setModel(Model model) {
        this.model = model;

        model.addAttribute("CONTEXT", this);

        return this;
    }

    public ViewContext setView(String module, String action) {
        this.module = module;
        this.action = action;

        return this;
    }

    public String getModule() {
        return module;
    }

    public ViewContext setModule(String module) {
        this.module = module;

        return this;
    }

    public String getAction() {
        return action;
    }

    public ViewContext setAction(String action) {
        this.action = action;

        return this;
    }

    public String fetch() {
        return "";
    }
}
