package com.jquantium.helper;

import com.jquantium.bean.Url;
import com.jquantium.bean.core.Context;
import com.jquantium.bean.view.PageContext;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mykhailo_Bohdanov on 03/07/2015.
 */
public class ContextHelper {

    public static Context newContext(HttpServletRequest request, HttpServletResponse response) {
        return new Context(request, response);
    }

    public static PageContext newPageContext(HttpServletRequest request, Model model) {
        return newPageContext(request, null, model, null);
    }
    public static PageContext newPageContext(HttpServletRequest request, Model model, Url url) {
        return newPageContext(request, null, model, url);
    }
    public static PageContext newPageContext(HttpServletRequest request, HttpServletResponse response, Model model) {
        return newPageContext(request, response, model, null);
    }
    public static PageContext newPageContext(HttpServletRequest request, HttpServletResponse response, Model model, Url url) {
        PageContext PC  = new PageContext(request, response, model);

        PC.setLanguage();

        if (url == null) {
            PC.setUrl(new Url(request));
        } else {
            PC.setUrl(url);
        }

        return PC;
    }

    public static boolean hasAjax(HttpServletRequest request) {
        String param = null;
        boolean ajax = false;

        try {
            if ((param = request.getHeader("X-REQUESTED-WITH")) != null && "XMLHttpRequest".equals(param)) {
                ajax = true;
            }
        } catch (Exception e){}

        if (!ajax) {
            try {
                if ((param = request.getHeader("HTTP-X-REQUESTED-WITH")) != null && "XMLHttpRequest".equals(param)) {
                    ajax = true;
                }
            } catch (Exception e) {}

            if (!ajax || (param = request.getParameter("_ajax")) != null) {
                try {
                    if ("true".equals(param)) {
                        ajax = true;
                    } else if ("false".equals(param)) {
                        ajax = false;
                    }
                } catch (Exception e) {}
            }
        }

        return ajax;
    }


}
