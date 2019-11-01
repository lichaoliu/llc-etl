package com.llc.etl.admin.controller.interceptor;

import com.llc.etl.admin.controller.annotation.PermessionLimit;
import com.llc.etl.admin.core.util.CookieUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 * @program: llc-etl
 * @description: 权限拦截
 * @author:
 * @create: 2019-10-24 11:01
 */
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

    @Value("admin")
    private String username;
    @Value("123456")
    private String password;

    public static final String LOGIN_IDENTITY_KEY = "LLC_ETL_IDENTITY";
    private static String LOGIN_IDENTITY_TOKEN;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0) {
            throw new RuntimeException("账号密码不可为空");
        }
        String tokenTmp = DigestUtils.md5DigestAsHex(String.valueOf(username + "_" + password).getBytes());
        tokenTmp = new BigInteger(1, tokenTmp.getBytes()).toString(16);
        LOGIN_IDENTITY_TOKEN = tokenTmp;
    }

    public static String getLoginIdentityToken() {
        return LOGIN_IDENTITY_TOKEN;
    }

    public static boolean ifLogin(HttpServletRequest request) {
        String indentityInfo = CookieUtil.getValue(request, LOGIN_IDENTITY_KEY);
        if (indentityInfo == null || !getLoginIdentityToken().equals(indentityInfo.trim())) {
            return false;
        }
        return true;
    }

    public static boolean login(HttpServletResponse response, String username, String password, boolean ifRemember) {
        String tokenTmp = DigestUtils.md5DigestAsHex(String.valueOf(username + "_" + password).getBytes());
        tokenTmp = new BigInteger(1, tokenTmp.getBytes()).toString(16);

        if (!getLoginIdentityToken().equals(tokenTmp)) {
            return false;
        }
        CookieUtil.set(response, LOGIN_IDENTITY_KEY, getLoginIdentityToken(), ifRemember);
        return true;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }

        if (!ifLogin(request)) {
            HandlerMethod method = (HandlerMethod) handler;
            PermessionLimit permission = method.getMethodAnnotation(PermessionLimit.class);
            if (permission == null || permission.limit()) {
                response.sendRedirect(request.getContextPath().concat("/toLogin"));
                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }

}
