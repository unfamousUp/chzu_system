package com.chzu.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查
 * 1.配置好拦截器要拦截哪些请求
 * 2.把这些配置放到容器中
 */
@Slf4j // lombok提供的日志
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 目标方法执行之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取所有请求路径
        String requestURI = request.getRequestURI();
        log.info("拦截的请求路径是{}", requestURI); // 打印
        // if (requestURI.contains("/doc.html")) {
        //     return true;
        // } else {
        // 登录检查逻辑
        HttpSession session = request.getSession();

        Object user_login = session.getAttribute("user_login");

        if (user_login != null) {
            return true;
        }
        // 拦截成功，跳转到登录页逻辑
        return false;
}

    /**
     * 目标方法执行完成以后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 目标方法执行完成以后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
