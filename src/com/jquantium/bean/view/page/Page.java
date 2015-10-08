package com.jquantium.bean.view.page;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class Page {
    private int pageId;
    private String name;
    private String url;

    public Page(int pageId, String name, String url) {
        this.pageId = pageId;
        this.name = name;
        this.url = url;
    }

    public int getPageId() {
        return pageId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
