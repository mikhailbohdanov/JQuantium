package com.jquantium.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Михаил on 21.07.2015.
 */
@Controller
@RequestMapping("/security")
public class Security {

    @RequestMapping(value = "/auth")
    public JSONObject auth(HttpServletRequest request, HttpServletResponse response) {


        return null;
    }


}
