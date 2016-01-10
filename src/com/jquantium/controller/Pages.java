package com.jquantium.controller;

import com.jquantium.bean.Url;
import com.jquantium.bean.core.Route;
import com.jquantium.bean.view.PageContext;
import com.jquantium.bean.view.page.Page;
import com.jquantium.bean.view.page.PageView;
import com.jquantium.helper.ContextHelper;
import com.jquantium.service.CORE;
import com.jquantium.util.error.PageNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Controller
public class Pages {

//    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String getPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        PageContext PC = ContextHelper.newPageContext(request, response, model);

        Url url             = PC.getUrl();
        String currentUrl   = url.getPath();
        Page page           = null;
        Route route         = null;

        try {
            page            = CORE.view.getPage(currentUrl);
        } catch (PageNotFoundException e) {}

        if (page == null) {
            try {
                route       = CORE.router.getRoute(currentUrl.replaceAll("^/", ""));

                if (route != null) {
                    switch (route.getType()) {
                        case PAGE:
                            page = CORE.view.getPage(route.getOwnerId());
                            break;
                        case USER:

                            break;
                        case GROUP:

                            break;
                        default:

                            break;
                    }
                }
            } catch (PageNotFoundException e) {}
        }

        if (page != null) {
            PC.setPage(page);

            if (checkPageAccess(PC)) {
                PageView pageView = getPageView(PC);

                if (pageView != null) {
                    //TODO here build page by Page
                } else {
                    //TODO some trouble
                }
            } else {
                //TODO here 403 error
            }
        } else {
            //TODO here 404 error
        }

        return PC.setView("render", "page").fetch();
    }

    public boolean checkPageAccess(PageContext PC) {
        return true;
    }

    public PageView getPageView(PageContext PC) {
        return null;
    }



}
