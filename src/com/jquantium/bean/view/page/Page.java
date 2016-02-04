package com.jquantium.bean.view.page;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Table(name = "view_pages")
public class Page {
    public enum Status {
        PUBLIC,
        MODIFIED,
        PRIVATE,
        DELETED
    }

    @Column(primary = true, length = 10, unsigned = true, autoIncrement = true)
    private int pageId;

    @Column(unique = "name", length = 128, notNull = true)
    private String name;

    @Column(length = 0)
    private String url;

    @Column
    private Status status;

    public Page() {}

    public Page(int pageId, String name, String url, Status status) {
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

    public Status getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
