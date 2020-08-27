package com.liu.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.SysLog;
import com.liu.service.system.SysLogService;
import com.liu.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/system/log")
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5") int pageSize){

        PageInfo<SysLog> pageInfo = sysLogService.findByPage(getLoginCompanyId(), pageNum, pageSize);
        request.setAttribute("pageInfo",pageInfo);
        return "/system/log/log-list";
    }
}
