package com.kuebikoit.deviceengine.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
@Configuration
public class Interceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception {
        log.info("MINIMAL: INTERCEPTOR PREHANDLE CALLED");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("MINIMAL: INTERCEPTOR POSTHANDLE CALLED");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        log.info("MINIMAL: INTERCEPTOR AFTERCOMPLETION CALLED");
    }
}