package com.jquantium.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mykhailo_Bohdanov on 07/10/2015.
 */
public class CORE {
    public static Localization localization;
    public static View view;
    public static Router router;
    public static Config config;




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
