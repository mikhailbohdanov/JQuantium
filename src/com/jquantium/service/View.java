package com.jquantium.service;

import com.jquantium.bean.view.page.Page;
import com.jquantium.util.auto.AutoHashMap;
import com.jquantium.util.auto.AutoList;
import com.jquantium.util.auto.AutoTreeMap;
import com.jquantium.util.error.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Service
public class View {
    private AutoList<Page> pages = new AutoList<>();

    private AutoHashMap<Integer, Page> pageById = new AutoHashMap<Integer, Page>(pages) {
        @Override
        public Integer getKey(Page page) {
            return page.getPageId();
        }
    };

    private AutoTreeMap<String, Page> pageByUrl = new AutoTreeMap<String, Page>(pages) {
        @Override
        public String getKey(Page page) {
            return page.getUrl();
        }
    };

    public Page getPage(String url) throws PageNotFoundException {
        return pageByUrl.get(url);
    }
    public Page getPage(int pageId) throws PageNotFoundException {
        return pageById.get(pageId);
    }



}
