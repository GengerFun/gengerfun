package com.hmall.user.config;

import com.hmall.user.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月16日 23:02
 */

@Component
@Slf4j
public class SpringMvcInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("authorization");
        BaseContext.setCurrentId(Long.parseLong(authorization));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
