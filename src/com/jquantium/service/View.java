package com.jquantium.service;

import com.jquantium.bean.view.page.Page;
import com.jquantium.dao.ORM;
import com.jquantium.util.auto.AHashMap;
import com.jquantium.util.auto.AList;
import com.jquantium.util.auto.AMap;
import com.jquantium.util.auto.ATreeMap;
import com.jquantium.util.error.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Service
public class View {
    @Autowired
    private ORM orm;

    private AList<Page> pageList = new AList<Page>(orm) {
        @Override
        public List<Page> init() {
            try {
                return orm.selectList(Page.class);
            } catch (Exception e) {
                return null;
            }
        }
    };

    private AMap<Integer, Page> pageById = new AHashMap<Integer, Page>(pageList) {
        @Override
        public Integer getKey(Page page) {
            return page.getPageId();
        }
    };

    private AMap<String, Page> pageByUrl = new ATreeMap<String, Page>(pageList) {
        @Override
        public String getKey(Page page) {
            return page.getUrl();
        }
    };

    public Page getPage(int pageId) throws PageNotFoundException {
        if (pageId <= 0) {
            throw new PageNotFoundException();
        }

        return pageById.get(pageId);
    }

    public Page getPage(String url) throws PageNotFoundException {
        if (url == null || url.isEmpty()) {
            throw new PageNotFoundException();
        }

        return pageByUrl.get(url);
    }

}
