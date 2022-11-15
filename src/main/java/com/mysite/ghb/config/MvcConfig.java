package com.mysite.ghb.config;/*
 * @author GongHb

*/

import com.mysite.ghb.controller.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("main");
        registry.addViewController("/administer.html").setViewName("administer");
        registry.addViewController("/admin/login.html").setViewName("admin/login");
        registry.addViewController("/admin").setViewName("admin/login");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/admin/*")//拦截
                .excludePathPatterns("/admin/login");
              /*  .excludePathPatterns("/login.html","/","/user/login","/user/register",
                        "/index_files/*",
                        "/404.html","/main.html","/static/*****","/main_files/*","/administer.html",
                        "/admin/*","/admin",
                        "/_fragments.html","/_fragments","/index.html");//排除*/
    }



}
