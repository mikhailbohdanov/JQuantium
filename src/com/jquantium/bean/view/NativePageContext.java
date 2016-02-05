package com.jquantium.bean.view;

import com.jquantium.bean.core.Context;
import com.jquantium.bean.view.page.Page;
import com.jquantium.bean.view.page.PageView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jquantium.util.Assert.Null;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class NativePageContext extends Context {
    private Page page;
    private PageView pageView;

    protected NativePageContext() {
        super();
    }

    public NativePageContext(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public Page getPage() {
        return page;
    }
    public NativePageContext setPage(Page page) {
        this.page = page;

        return this;
    }

    public PageView getPageView() {
        return pageView;
    }
    public NativePageContext setPageView(PageView pageView) {
        this.pageView = pageView;

        return this;
    }

    public NativePageContext initPage(String title, String keywords, String description) {
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
        if (Null(pageView)) {
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
