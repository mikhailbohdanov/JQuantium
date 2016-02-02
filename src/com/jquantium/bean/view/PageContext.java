package com.jquantium.bean.view;

import com.jquantium.bean.core.Context;
import com.jquantium.bean.localization.Language;
import com.jquantium.bean.view.page.Page;
import com.jquantium.bean.view.page.PageView;
import com.jquantium.service.CORE;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class PageContext extends Context {
    private Model model;
    private String module;
    private String action;

    private Page page;
    private PageView pageView;

    protected PageContext() {
        super();
    }

    public PageContext(HttpServletRequest request, HttpServletResponse response, Model model) {
        super(request, response);

        setModel(model);
    }

    public Model getModel() {
        return model;
    }
    private PageContext setModel(Model model) {
        this.model = model;

        model.addAttribute("PC", this);

        return this;
    }

    public PageContext setView(String module, String action) {
        this.module = module;
        this.action = action;

        return this;
    }

    public String getModule() {
        return module;
    }
    public PageContext setModule(String module) {
        this.module = module;

        return this;
    }

    public String getAction() {
        return action;
    }
    public PageContext setAction(String action) {
        this.action = action;

        return this;
    }

    public Page getPage() {
        return page;
    }
    public PageContext setPage(Page page) {
        this.page = page;

        return this;
    }

    public PageView getPageView() {
        return pageView;
    }
    public PageContext setPageView(PageView pageView) {
        this.pageView = pageView;

        return this;
    }

    public PageContext initPage(String title, String keywords, String description) {
        this.page       = new Page();
        this.pageView   = new PageView();

        this.pageView
                .setTitle(title)
                .setKeyWords(keywords)
                .setDescription(description);

        return this;
    }

    public String getTitle() {
        if (pageView == null) {
            return null;
        }

        return pageView.getTitle();
    }
    public String getKeywords() {
        if (pageView == null) {
            return null;
        }

        return pageView.getKeyWords();
    }
    public String getDescription() {
        if (pageView == null) {
            return null;
        }

        return pageView.getDescription();
    }

    public String fetch() {
        return "";
    }

}
