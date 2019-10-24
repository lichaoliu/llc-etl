package com.llc.etl.admin.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: llc-etl
 * @description: 权限拦截
 * @author:
 * @create: 2019-10-24 11:01
 */
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    public static boolean ifLogin(HttpServletRequest request){
        request.getCookies();
        return true;
    }

    public static void main(String[] argus){
        System.out.println(Integer.MAX_VALUE / (60*24*60));
    }

}
