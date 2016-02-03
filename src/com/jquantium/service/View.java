package com.jquantium.service;

import com.jquantium.bean.view.page.Page;
import com.jquantium.util.error.PageNotFoundException;
import com.jquantium.util.memory.MHashMap;
import com.jquantium.util.memory.MList;
import com.jquantium.util.memory.MTreeMap;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Service
public class View {

    private MList<Page> pageList = new MList<Page>() {
        @Override
        protected List<Page> init() {
            return null;
        }

        @Override
        protected boolean createElement(Page element) {
            return false;
        }

        @Override
        protected boolean updateElement(Page element) {
            return false;
        }

        @Override
        protected boolean removeElement(Page element) {
            return false;
        }
    };

    private MHashMap<Integer, Page> pageById = new MHashMap<Integer, Page>(pageList) {
        @Override
        public Integer getKey(Page page) {
            return page.getPageId();
        }
    };

    private MTreeMap<String, Page> pageByUrl = new MTreeMap<String, Page>(pageList) {
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
