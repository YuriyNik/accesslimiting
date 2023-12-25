package com.assigment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      //  registry.addInterceptor(new APIInterceptor())
      //          .addPathPatterns("/api/businesses/**"); // Specify the URL patterns for which the interceptor should be applied.
    }
}
