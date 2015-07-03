package com.jquantium.bean.core;

import com.jquantium.bean.Url;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mykhailo_Bohdanov on 03/07/2015.
 */
public class Context {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Url url;

    protected Context() {

    }

    public Context(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public Url getUrl() {
        return url;
    }
}
