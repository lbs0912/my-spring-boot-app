package com.lbs.spring.my_app.interceptor;

import com.lbs.spring.my_app.annotation.LoginRequired;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器了");
        System.out.println("\n-------- SourceAccessInterceptor.preHandle --- ");


        // 反射获取方法上的LoginRequired注解
        HandlerMethod handlerMethod = (HandlerMethod)handler;


        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(LoginRequired.class)){
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            //提示用户登录
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print("你访问的资源需要登录");
            return false; //返回false 进行拦截
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("\n-------- SourceAccessInterceptor.postHandle --- ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("\n-------- SourceAccessInterceptor.afterCompletion --- ");
    }
}
