package com.llc.etl.admin.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @program: llc-etl
 * @description:
 * @author:
 * @create: 2019-10-24 11:07
 */
@Component
public class CookieInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView!=null && request.getCookies()!=null && request.getCookies().length>0) {
            HashMap<String, Cookie> cookieMap = new HashMap<String, Cookie>();
            for (Cookie ck : request.getCookies()) {
                cookieMap.put(ck.getName(), ck);
            }
            modelAndView.addObject("cookieMap", cookieMap);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
