package com.healthserviceapp.configuration;

import com.healthserviceapp.interceptors.EditPageTitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorsConfig extends WebMvcConfigurerAdapter {

    private final EditPageTitleInterceptor editPageTitleInterceptor;

    @Autowired
    public InterceptorsConfig(EditPageTitleInterceptor editPageTitleInterceptor) {
        this.editPageTitleInterceptor = editPageTitleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.editPageTitleInterceptor);
    }
}
