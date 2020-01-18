package com.kuebikoit.deviceengine.config;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
@Configuration
public class Interceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(
      HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler)
      throws Exception {

    var user = Optional.ofNullable(SecurityContextHolder.getContext())
        .map(c -> c.getAuthentication())
        .map(a -> a.getPrincipal())
        .orElse("NONE");

    log.info("Logged in user={}", user);

    return true;
  }
}
