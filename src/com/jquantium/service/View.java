package com.jquantium.service;

import com.jquantium.bean.view.page.Page;
import com.jquantium.util.auto.AutoTreeMap;
import com.jquantium.util.error.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Service
public class View {
    private static AutoTreeMap<String, Page> pages = new AutoTreeMap<String, Page>() {


        @Override
        public String getKey(Page element) {
            return null;
        }
    };


    public static Page getPage(String url) throws PageNotFoundException {
        pages.get(url);

        return null;
    }
    public static Page getPage(int pageId) throws PageNotFoundException {


        return null;
    }

    @Autowired
    public void init() {

    }


}
