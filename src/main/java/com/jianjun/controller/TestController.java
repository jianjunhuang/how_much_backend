package com.jianjun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "/testing", method = RequestMethod.GET)
    @ResponseBody
    public String testIndex() {

        return "test";
    }
}