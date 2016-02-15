package com.jquantium.controller;

import com.jquantium.bean.Url;
import com.jquantium.bean.core.Route;
import com.jquantium.bean.view.PageContext;
import com.jquantium.bean.view.ViewContext;
import com.jquantium.bean.view.page.Page;
import com.jquantium.bean.view.page.PageView;
import com.jquantium.helper.ContextHelper;
import com.jquantium.service.Router;
import com.jquantium.service.View;
import com.jquantium.util.error.PageNotFoundException;
import com.jquantium.util.error.RouteNotFoundException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jquantium.util.Assert.NULL;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Controller
public class Pages {

    @Autowired
    private View view;

    @Autowired
    private Router router;

    @RequestMapping(value = "/**", method = RequestMethod.POST)
    public JSONObject getPageJSON(HttpServletRequest request, HttpServletResponse response) {
        PageContext context = ContextHelper.newPageContext(request, response);

        return null;
    }

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String getPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        ViewContext context = ContextHelper.newViewContext(request, response, model);

        Url url = context.getUrl();
        String currentUrl = url.getPath();
        Page page = null;
        Route route;

        try {
            page = view.getPage(currentUrl);
        } catch (PageNotFoundException e) {
        }

        if (NULL(page)) {
            try {
                route = router.getRoute(currentUrl.replaceAll("^/", ""));

                if (!NULL(route)) {
                    switch (route.getType()) {
                        case PAGE:
                            page = view.getPage(route.getOwnerId());
                            break;
                        case USER:

                            break;
                        case GROUP:

                            break;
                        default:

                            break;
                    }
                }
            } catch (RouteNotFoundException | PageNotFoundException e) {
            }
        }

        if (!NULL(page)) {
            context.setPage(page);

            if (checkPageAccess(context)) {
                PageView pageView = getPageView(context);

                if (!NULL(pageView)) {
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

        return context.setView("render", "page").fetch();
    }

    public boolean checkPageAccess(PageContext PC) {
        return true;
    }

    public PageView getPageView(ViewContext VC) {
        return null;
    }

}
