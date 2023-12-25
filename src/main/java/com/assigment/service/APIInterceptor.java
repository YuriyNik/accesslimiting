package com.assigment.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

public class APIInterceptor implements HandlerInterceptor {

    @Autowired
    private QuotaService quotaService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // This method is called before the actual handler method is invoked.
        // You can perform pre-processing here.
        // If you want to proceed with the request, return true; otherwise, set the response and return false.

        String pathVariables = (String) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        System.out.println("preHandle intercepter="+pathVariables);
        /*
        if (quotaService.consumeQuota(userId)) {
            return ResponseEntity.ok("Quota consumed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Quota exceeded");
        }

         */

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // This method is called after the handler method is invoked but before the view is rendered.

        // You can perform post-processing here.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // This method is called after the request has been handled and the view has been rendered.

        // You can perform cleanup or additional logging here.
    }
}
