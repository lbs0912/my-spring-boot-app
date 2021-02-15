package com.lbs.spring.my_app.config;

import com.lbs.spring.my_app.interceptor.AdminInterceptor;
import com.lbs.spring.my_app.interceptor.LogInterceptor;
import com.lbs.spring.my_app.interceptor.OldLoginInterceptor;
import com.lbs.spring.my_app.interceptor.SourceAccessInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SourceAccessInterceptor()).addPathPatterns("/**");

//        // LogInterceptor apply to all URLs.
//        registry.addInterceptor(new LogInterceptor());
//
//        // Old Login url, no longer use.
//        // Use OldURLInterceptor to redirect to a new URL.
//        registry.addInterceptor(new OldLoginInterceptor())//
//                .addPathPatterns("/admin/oldLogin");
//
//        // This interceptor apply to URL like /admin/*
//        // Exclude /admin/oldLogin
//        registry.addInterceptor(new AdminInterceptor())//
//                .addPathPatterns("/admin/*")//
//                .excludePathPatterns("/admin/oldLogin");
    }

}