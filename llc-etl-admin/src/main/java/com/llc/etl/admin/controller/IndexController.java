package com.llc.etl.admin.controller;

import com.llc.etl.admin.constant.MessageConsts;
import com.llc.etl.admin.constant.PageRouteConsts;
import com.llc.etl.admin.controller.annotation.PermessionLimit;
import com.llc.etl.admin.controller.interceptor.PermissionInterceptor;
import com.llc.etl.admin.core.result.ReturnT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: llc-etl
 * @description:
 * @author:
 * @create: 2019-10-22 16:09
 */
@Controller
public class IndexController {

    /**
     * 记住账号密码
     */
    private static final String ON = "on";

    @RequestMapping("/toLogin")
    @PermessionLimit(limit = false)
    public String toLogin(Model model, HttpServletRequest request) {
        if (PermissionInterceptor.ifLogin(request)) {
            return "redirect:/";
        }
        return PageRouteConsts.LOGIN;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    @PermessionLimit(limit = false)
    public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember) {
        if (PermissionInterceptor.ifLogin(request)) {
            return ReturnT.SUCCESS;
        }
        if (userName == null || userName.trim().length() == 0 || password == null || password.trim().length() == 0) {
            return new ReturnT(ReturnT.FAIL_CODE, MessageConsts.PLEASE_PASSWORD);
        }
        boolean ifRem = (ifRemember != null && ON.equals(ifRemember)) ? true : false;
        boolean loginRet = PermissionInterceptor.login(response, userName, password, ifRem);
        if (!loginRet) {
            return new ReturnT<>(ReturnT.FAIL_CODE, MessageConsts.ACCOUNT_PASSWORD_ERROR);
        }
        return ReturnT.SUCCESS;
    }

}
