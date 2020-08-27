package com.liu.web.controller;

import com.liu.domain.system.Module;
import com.liu.domain.system.User;
import com.liu.service.system.ModuleService;
import com.liu.service.system.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    //登录
    /*@RequestMapping("/login")
    public String login(String email, String password) {

        //没填登录账号密码
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return "forward:login.jsp";
        }
        //判断账号密码登录
        User user = userService.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("loginUser", user);

                List<Module> modules= moduleService.findModuleByUserId(user.getId());
                session.setAttribute("modules",modules);

                return "home/main";
            }
        }
        request.setAttribute("error","用户名或者密码错误");
        return "forward:/login.jsp";
    }*/

    //shiro提供的认证
    @RequestMapping("/login")
    public String login(String email, String password) {
        //没填登录账号密码
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return "forward:login.jsp";
        }

        try {
            /* shiro 认证 */
            //1. 获取shiro的subject对象，在shiro中表示当前的登陆用户
            Subject subject = SecurityUtils.getSubject();

            //2. 创建token封装账号密码
            AuthenticationToken token = new UsernamePasswordToken(email, password);

            //3. 登陆认证
            subject.login(token);

            //4. 登陆认证成功（0） 先获取登陆用户的身份对象 (realm认证方法返回对象的构造函数的第一个参数)
            User user = (User) subject.getPrincipal();

            //4. 登陆认证成功（1） 保存登陆用户到session
            session.setAttribute("loginUser",user);

            //4. 登陆认证成功（2） 查询用户的权限作为动态菜单数据显示
            List<Module> modules = moduleService.findModuleByUserId(user.getId());
            session.setAttribute("modules",modules);
            return "home/main";

        } catch (AuthenticationException e) {
        // 登陆失败
            e.printStackTrace();
            request.setAttribute("error","用户名或者密码错误");
            return "forward:/login.jsp";
        }

    }


    //注销
//    @RequestMapping("/logout")
//    public String logout(){
//        // 先释放资源
//        SecurityUtils.getSubject().logout();
//        session.removeAttribute("loginUser");
//        //销毁session对象
//        session.invalidate();
//        return "forward:/login.jsp";
//    }

    @RequestMapping("/home")
    public String home() {
        return "home/home";
    }

}
