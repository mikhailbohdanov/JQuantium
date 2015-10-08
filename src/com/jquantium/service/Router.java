package com.jquantium.service;

import com.jquantium.bean.core.Route;
import com.jquantium.util.auto.AutoHashMap;
import com.jquantium.util.auto.AutoList;
import com.jquantium.util.auto.AutoTreeMap;
import com.jquantium.util.error.PageNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Mykhailo_Bohdanov on 08/07/2015.
 */
@Service
public class Router {
    private AutoList<Route> routes = new AutoList<>();
    private AutoHashMap<Integer, Route> routesById = new AutoHashMap<Integer, Route>(routes) {
        @Override
        public Integer getKey(Route route) {
            return route.getRouteId();
        }
    };
    private AutoTreeMap<String, Route> routesByUrl = new AutoTreeMap<String, Route>(routes) {
        @Override
        public String getKey(Route route) {
            return route.getUrl();
        }
    };


    public Route getRoute(int routeId) throws PageNotFoundException {
        if (routeId <= 0) {
            throw new PageNotFoundException();
        }

        return routesById.get(routeId);
    }

    public Route getRoute(String url) throws PageNotFoundException {
        if (url == null || url.isEmpty()) {
            throw new PageNotFoundException();
        }

        return routesByUrl.get(url);
    }



}
