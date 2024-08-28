package com.hmall.user.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignInterceptorConfig {
  /**
   * feign请求拦截器
   */
  @Bean
  public RequestInterceptor requestInterceptor(){
    return new FeignRequestInterceptor();
  }
}