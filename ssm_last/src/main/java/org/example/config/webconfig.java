package org.example.config;

import org.example.interceptors.headlineinterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class webconfig implements WebMvcConfigurer {

    @Autowired
    private headlineinterceptors hltors;


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(hltors).addPathPatterns("/headline/**");

    }

}
