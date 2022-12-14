package com.mysite.ghb.controller;/*
 * @author GongHb

*/

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       Object loginUser=request.getSession().getAttribute("admin");
       if (loginUser==null){
           request.setAttribute("msg","没有权限，请先登录");
           request.getRequestDispatcher("/admin.html").forward(request,response);
           return false;
       }else {
           return true;
       }

    }

}
