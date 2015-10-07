package com.jquantium.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Михаил on 21.07.2015.
 */
@Controller
@RequestMapping("/security")
public class Security {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject logout(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject signup(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

}
