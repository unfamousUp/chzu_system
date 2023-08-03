// package com.chzu.filter;
//
// import org.springframework.stereotype.Component;
//
// import javax.servlet.*;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
//
// @Component
// public class CorsFilter implements Filter {
//
//     @Override
//     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//         HttpServletResponse response = (HttpServletResponse) servletResponse;
//         HttpServletRequest request = (HttpServletRequest) servletRequest;
//         //跨域设置,谁来都放行,与设置成*效果相同,但是这里设置成*行不通,因此用该方法代替
//         response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//         response.setHeader("Access-Control-Allow-Credentials", "true");
//         //不能设置成*,否则跨域请求会失败
//         response.setHeader("Access-Control-Allow-Methods", "POST,PUT, GET, OPTIONS, DELETE");
//         response.setHeader("Access-Control-Max-Age", "3600");
//         //我这里需要放行这三个header头部字段
//         response.setHeader("Access-Control-Allow-Headers", "content-type,x-requested-with,token");
//         try {
//             filterChain.doFilter(request, response);
//         } catch (IOException e) {
//             e.printStackTrace();
//         } catch (ServletException e) {
//             e.printStackTrace();
//         }
//     }
//
//     public void destroy() {
//     }
// }