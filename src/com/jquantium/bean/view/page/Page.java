package com.jquantium.bean.view.page;

import com.jquantium.dao.annotation.Table;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Table(name = "view_pages")
public class Page {
    public enum STATUS {
        PUBLIC,
        MODIFIED,
        PRIVATE,
        DELETED
    }

    private int pageId;
    private String name;
    private String url;
    private STATUS status;

    public Page() {}

    public Page(int pageId, String name, String url, STATUS status) {
        this.pageId = pageId;
        this.name = name;
        this.url = url;
        this.status = status;
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

    public STATUS getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = STATUS.valueOf(status);
    }
    public void setStatus(STATUS status) {
        this.status = status;
    }
}
