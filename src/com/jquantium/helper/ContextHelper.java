package com.jquantium.helper;

import com.jquantium.bean.core.Context;
import com.jquantium.bean.view.PageContext;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mykhailo_Bohdanov on 03/07/2015.
 */
public class ContextHelper {

    public static Context getContext(HttpServletRequest request, HttpServletResponse response) {
        return new Context();
    }

    public static PageContext getPageContext(HttpServletRequest request) {
        return getPageContext(request, null, null);
    }
    public static PageContext getPageContext(HttpServletRequest request, HttpServletResponse response) {
        return getPageContext(request, response, null);
    }
    public static PageContext getPageContext(HttpServletRequest request, HttpServletResponse response, Model model) {
        return new PageContext();
    }



}
