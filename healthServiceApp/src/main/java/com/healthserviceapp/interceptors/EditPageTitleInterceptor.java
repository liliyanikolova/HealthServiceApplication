package com.healthserviceapp.interceptors;

import com.healthserviceapp.areas.common.utils.PageTitles;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class EditPageTitleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //boolean asyncCall = request.getRequestURI().toLowerCase().contains("async");

        if(modelAndView == null ){ return; }

        boolean ajax = "XMLHttpRequest".equals(
                request.getHeader("X-Requested-With"));

        if (!ajax) {
            String pageTitle = (String) modelAndView.getModelMap().get("title");
            String newTitle = PageTitles.APPLICATION_NAME;
            if (pageTitle != null) {
                newTitle += " - " + pageTitle;
            }

            modelAndView.addObject("title", newTitle);
        }
    }
}
