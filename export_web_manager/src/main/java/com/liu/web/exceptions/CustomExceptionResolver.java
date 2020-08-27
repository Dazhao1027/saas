package com.liu.web.exceptions;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

/*        ex.printStackTrace();
        ModelAndView mv = new ModelAndView("error");

        mv.addObject("errorMsg","is my error");
        mv.addObject("ex",ex);
        return mv;*/
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView();
        if (ex instanceof UnauthorizedException){
            mv.setViewName("unauthorized");
        }else {
            mv.setViewName("error");
        }
        mv.addObject("errorMsg",ex.getMessage());
        return mv;
    }

}
