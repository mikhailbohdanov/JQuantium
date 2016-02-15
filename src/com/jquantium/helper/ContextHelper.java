package com.jquantium.helper;

import com.jquantium.bean.Url;
import com.jquantium.bean.core.Context;
import com.jquantium.bean.view.PageContext;
import com.jquantium.bean.view.ViewContext;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jquantium.util.Assert.EQUALS;
import static com.jquantium.util.Assert.NULL;

/**
 * Created by Mykhailo_Bohdanov on 03/07/2015.
 */
public class ContextHelper {
    public static void initContext(Context context, HttpServletRequest request, HttpServletResponse response, Url url) {
        context.setLanguage();

        if (NULL(url)) {
            context.setUrl(new Url(request));
        } else {
            context.setUrl(url);
        }
    }

    public static Context newContext(HttpServletRequest request, HttpServletResponse response) {
        return new Context(request, response);
    }

    public static PageContext newPageContext(HttpServletRequest request) {
        return newPageContext(request, null, null);
    }
    public static PageContext newPageContext(HttpServletRequest request, Url url) {
        return newPageContext(request, null, url);
    }
    public static PageContext newPageContext(HttpServletRequest request, HttpServletResponse response) {
        return newPageContext(request, response, null);
    }
    public static PageContext newPageContext(HttpServletRequest request, HttpServletResponse response, Url url) {
        PageContext PC = new PageContext(request, response);

        initContext(PC, request, response, url);

        return PC;
    }

    public static ViewContext newViewContext(HttpServletRequest request, HttpServletResponse response, Model model) {
        return newViewContext(request, response, model, null);
    }
    public static ViewContext newViewContext(HttpServletRequest request, HttpServletResponse response, Model model, Url url) {
        ViewContext VC = new ViewContext(request, response);

        VC.setModel(model);
        initContext(VC, request, response, url);

        return VC;
    }



    public static boolean hasAjax(HttpServletRequest request) {
        String param = null;
        boolean ajax = false;

        try {
            if (!NULL(param = request.getHeader("X-REQUESTED-WITH")) && EQUALS("XMLHttpRequest", param)) {
                ajax = true;
            }
        } catch (Exception e) {
        }

        if (!ajax) {
            try {
                if (!NULL(param = request.getHeader("HTTP-X-REQUESTED-WITH")) && EQUALS("XMLHttpRequest", param)) {
                    ajax = true;
                }
            } catch (Exception e) {
            }

            if (!ajax || !NULL(param = request.getParameter("_ajax"))) {
                try {
                    if (EQUALS("true", param)) {
                        ajax = true;
                    } else if (EQUALS("false", param)) {
                        ajax = false;
                    }
                } catch (Exception e) {
                }
            }
        }

        return ajax;
    }


}
