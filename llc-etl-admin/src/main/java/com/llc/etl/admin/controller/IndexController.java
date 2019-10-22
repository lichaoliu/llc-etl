package com.llc.etl.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: llc-etl
 * @description:
 * @author:
 * @create: 2019-10-22 16:09
 */
@Controller
public class IndexController {

    @RequestMapping("/toLogin")
    public String toLogin(Model model, HttpServletRequest request) {
        System.out.println("------------");
        return "login";
    }

}
