package com.jquantium.bean.view;

import com.jquantium.bean.core.Context;
import com.jquantium.bean.view.page.Page;
import com.jquantium.bean.view.page.PageView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jquantium.util.Assert.NULL;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class PageContext extends Context {
    private Page page;
    private PageView pageView;

    protected PageContext() {
        super();
    }

    public PageContext(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
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
        this.page = new Page();
        this.pageView = new PageView();

        this.pageView
                .setTitle(title)
                .setKeyWords(keywords)
                .setDescription(description);

        return this;
    }

    public String getTitle() {
        if (NULL(pageView)) {
            return null;
        }

        return pageView.getTitle();
    }

    public String getKeywords() {
        if (NULL(pageView)) {
            return null;
        }

        return pageView.getKeyWords();
    }

    public String getDescription() {
        if (NULL(pageView)) {
            return null;
        }

        return pageView.getDescription();
    }

}
