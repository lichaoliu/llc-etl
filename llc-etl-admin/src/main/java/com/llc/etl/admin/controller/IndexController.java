package com.llc.etl.admin.controller;

import com.llc.etl.admin.controller.annotation.PermessionLimit;
import com.llc.etl.admin.controller.interceptor.PermissionInterceptor;
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
    @PermessionLimit(limit=false)
    public String toLogin(Model model, HttpServletRequest request) {
        if(PermissionInterceptor.ifLogin(request)){
            return "redirect:/";
        }
        System.out.println("------------");
        return "login";
    }

}
