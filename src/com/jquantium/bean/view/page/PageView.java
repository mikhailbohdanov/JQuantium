package com.jquantium.bean.view.page;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class PageView {
    private int pageId;

    private String title;
    private String keyWords;
    private String description;

    public PageView() {}

    public PageView(int pageId, String title, String keyWords, String description) {
        this.pageId = pageId;
        this.title = title;
        this.keyWords = keyWords;
        this.description = description;
    }

    public int getPageId() {
        return pageId;
    }

    public String getTitle() {
        return title;
    }
    public PageView setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getKeyWords() {
        return keyWords;
    }
    public PageView setKeyWords(String keyWords) {
        this.keyWords = keyWords;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public PageView setDescription(String description) {
        this.description = description;
        return this;
    }
}
