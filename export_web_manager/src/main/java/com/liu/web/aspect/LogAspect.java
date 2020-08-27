package com.liu.web.aspect;

import com.liu.domain.system.SysLog;
import com.liu.domain.system.User;
import com.liu.service.system.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LogAspect {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;

    @Around("execution(* com.liu.web.controller..*.*(..)) && !bean(sysLogController)")
    public Object around(ProceedingJoinPoint pjp) {
        try {

            Object retV = pjp.proceed();

            SysLog log = new SysLog();
            log.setTime(new Date());
            log.setIp(request.getRemoteAddr());
            // 获取当前执行的方法，并设置到日志对象中
            log.setMethod(pjp.getSignature().getName());
            // 获取当前执行的目标对象全名，并设置到日志对象中
            log.setAction(pjp.getTarget().getClass().getName());
            //获取登录用户
            User user = (User) request.getSession().getAttribute("loginUser");

            if (user !=null){
                log.setUserName(user.getUserName());
                log.setCompanyId(user.getCompanyId());
                log.setCompanyName(user.getCompanyName());
            }
            sysLogService.save(log);
            return retV;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
