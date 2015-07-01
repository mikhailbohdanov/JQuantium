package com.jquantium.controller;

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

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String getPage(HttpServletRequest request, HttpServletResponse response, Model model) {





        return "";
    }

}
