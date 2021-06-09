package com.share.charge.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class testMVC {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request, HttpServletResponse response){
        return "index";
    }
}
