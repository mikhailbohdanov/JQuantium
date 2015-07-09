package com.jquantium.bean.core;

/**
 * Created by Mykhailo_Bohdanov on 06/07/2015.
 */
public class Route {
    public enum Type {
        PAGE,
        USER,
        GROUP
    }

    private int routeId;
    private int ownerId;
    private Type type;
    private String url;

    public Route(int routeId, int ownerId, Type type, String url) {
        this.routeId = routeId;
        this.ownerId = ownerId;
        this.type = type;
        this.url = url;
    }

    public int getRouteId() {
        return routeId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public Type getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
