package com.liu.web.controller;

import com.liu.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;

    /**
     * 登录用户的企业id
     */
    public String getLoginCompanyId(){
        return getLoginUser().getCompanyId();
    }
    /**
     * 获取登陆用户的企业名称
     */
    public String getLoginCompanyName(){
        return getLoginUser().getCompanyName();
    }

    public User getLoginUser(){
        return (User) session.getAttribute("loginUser");
    }
}
