package com.jquantium.service;

import com.jquantium.dao.ORM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mykhailo_Bohdanov on 07/10/2015.
 */
@Service
public class CORE {
//    public static ORM orm;
    public static Nodes nodes;
    public static Localization localization;
    public static View view;
    public static Router router;
    public static Config config;

//    @Autowired
//    public void setOrm(ORM orm) {
//        CORE.orm = orm;
//    }

    @Autowired
    public void setNodes(Nodes nodes) {
        CORE.nodes = nodes;
    }

    @Autowired
    public void setLocalization(Localization localization) {
        CORE.localization = localization;
    }

    @Autowired
    public void setView(View view) {
        CORE.view = view;
    }

    @Autowired
    public void setRouter(Router router) {
        CORE.router = router;
    }

    @Autowired
    public void setConfig(Config config) {
        CORE.config = config;
    }
}
