package com.chzu.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @Slf4j
// public class CORSUserFilter extends UserFilter {
//
//     @Override
//     protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//         log.info("CORSUserFilter: preHandle called");
//         HttpServletRequest httpRequest = WebUtils.toHttp(request);
//         HttpServletResponse httpResponse = WebUtils.toHttp(response);
//         if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//             httpResponse.setHeader("Access-control-Allow-Origin", httpRequest.getHeader("origin")); // origin
//             httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT, OPTIONS");
//             // httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
//             httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
//             // httpResponse.setHeader("Access-Control-Allow-Credentials","true");
//             httpResponse.setStatus(HttpStatus.OK.value());
//             return false;
//         }
//         return super.preHandle(request, response);
//     }
//
//     private void setHeader(HttpServletRequest request, HttpServletResponse response){
//         //跨域的header设置
//         response.setHeader("Access-control-Allow-Origin", "*"); //request.getHeader("Origin")
//         // response.setHeader("Access-Control-Allow-Methods", request.getMethod());
//         response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT, OPTIONS");
//         response.setHeader("Access-Control-Allow-Credentials", "true");
//         response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
//         //防止乱码，适用于传输JSON数据
//         response.setHeader("Content-Type","application/json;charset=UTF-8");
//         response.setStatus(HttpStatus.OK.value());
//     }
//
//     @Override
//     protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//         HttpServletResponse httpResp = WebUtils.toHttp(response);
//         HttpServletRequest httpReq = WebUtils.toHttp(request);
//
//         /*系统重定向会默认把请求头清空，这里通过拦截器重新设置请求头，解决跨域问题*/
//         httpResp.addHeader("Access-Control-Allow-Origin", httpReq.getHeader("Origin"));
//         httpResp.addHeader("Access-Control-Allow-Headers", "*");
//         httpResp.addHeader("Access-Control-Allow-Methods", "*");
//         // httpResp.addHeader("Access-Control-Allow-Credentials", "true");
//
//         this.saveRequestAndRedirectToLogin(request, response);
//         return false;
//     }
// }
@Slf4j
public class CORSUserFilter extends PathMatchingFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        configHeaders(httpRequest, httpResponse);//options和其他方法共用
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {

            log.debug("收到一个OPTIONS请求--"+httpRequest.getRequestURI());
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }

        return super.preHandle(request, response);
    }


    private void configHeaders(HttpServletRequest request, HttpServletResponse response){
        //↓ 该部分均可按照自己需要自行订制，这里只是做个参考

        response.setHeader("Access-Control-Allow-Origin", "http://192.168.85.107:6002");//TODO 配置你自己允许的前端源
        response.setHeader("Access-Control-Allow-Methods", "POST,PUT, GET, OPTIONS, DELETE");
        // response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
    }

    //     @Override
//     protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//         HttpServletResponse httpResp = WebUtils.toHttp(response);
//         HttpServletRequest httpReq = WebUtils.toHttp(request);
//
//         /*系统重定向会默认把请求头清空，这里通过拦截器重新设置请求头，解决跨域问题*/
//         httpResp.addHeader("Access-Control-Allow-Origin", httpReq.getHeader("Origin"));
//         httpResp.addHeader("Access-Control-Allow-Headers", "*");
//         httpResp.addHeader("Access-Control-Allow-Methods", "*");
//         // httpResp.addHeader("Access-Control-Allow-Credentials", "true");
//
//         this.saveRequestAndRedirectToLogin(request, response);
//         return false;
//     }

}