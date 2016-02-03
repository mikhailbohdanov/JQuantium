package com.jquantium.service;

import com.jquantium.bean.core.Route;
import com.jquantium.util.error.RouteNotFoundException;
import com.jquantium.util.memory.MHashMap;
import com.jquantium.util.memory.MList;
import com.jquantium.util.memory.MTreeMap;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 08/07/2015.
 */
@Service
public class Router {
    private MList<Route> routeList = new MList<Route>() {
        @Override
        protected List<Route> init() {
            return null;
        }

        @Override
        protected boolean createElement(Route element) {
            return false;
        }

        @Override
        protected boolean updateElement(Route element) {
            return false;
        }

        @Override
        protected boolean removeElement(Route element) {
            return false;
        }
    };

    private MHashMap<Integer, Route> routesById = new MHashMap<Integer, Route>(routeList) {
        @Override
        public Integer getKey(Route route) {
            return route.getRouteId();
        }
    };

    private MTreeMap<String, Route> routesByUrl = new MTreeMap<String, Route>(routeList) {
        @Override
        public String getKey(Route route) {
            return route.getUrl();
        }
    };

    public Route getRoute(int routeId) throws RouteNotFoundException {
        if (routeId <= 0) {
            throw new RouteNotFoundException();
        }

        return routesById.get(routeId);
    }

    public Route getRoute(String url) throws RouteNotFoundException {
        if (url == null || url.isEmpty()) {
            throw new RouteNotFoundException();
        }

        return routesByUrl.get(url);
    }



}
