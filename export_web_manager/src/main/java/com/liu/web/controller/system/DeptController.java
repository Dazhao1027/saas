package com.liu.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.Dept;
import com.liu.service.system.DeptService;
import com.liu.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
        String companyId=getLoginCompanyId();
        PageInfo<Dept> pageInfo = deptService.findAll(companyId, pageNum, pageSize);

        request.setAttribute("pageInfo",pageInfo);

        return "system/dept/dept-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){

        String companyId=getLoginCompanyId();
        List<Dept> deptList = deptService.findAll("1");
        request.setAttribute("deptList",deptList);
        return "system/dept/dept-add";
    }

    @RequestMapping("/edit")
    public String edit(Dept dept){
        String companyId=getLoginCompanyId();
        String companyName=getLoginCompanyName();
        dept.setCompanyId(companyId);
        dept.setCompanyName(companyName);

        if (StringUtils.isEmpty(dept.getId())){
            deptService.save(dept);
        }else {
            deptService.update(dept);
        }
        return "redirect:/system/dept/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){

        Dept dept = deptService.findById(id);

        List<Dept> deptList = deptService.findAll("1");

        request.setAttribute("dept",dept);
        request.setAttribute("deptList",deptList);
        return "system/dept/dept-update";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id){
        String result="ok";
        System.out.println(id);
        boolean flag = deptService.delete(id);

        if (!flag){
            result="error";
        }
        return result;
    }
}
