package com.jquantium.bean.view.page;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Table(name = "view_pages_{language}", template = true)
public class PageView {

    @Column(primary = true, length = 10, unsigned = true)
    private int pageId;

    @Column(length = 0)
    private String title;

    @Column(length = 0)
    private String keyWords;

    @Column(length = 0)
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
