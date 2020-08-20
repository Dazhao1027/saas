package com.liu.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.Role;
import com.liu.service.system.RoleService;
import com.liu.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
        String companyId=getLoginCompanyId();
        PageInfo<Role> pageInfo = roleService.findByPage(companyId, pageNum, pageSize);

        request.setAttribute("pageInfo",pageInfo);

        return "system/role/role-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){

        String companyId=getLoginCompanyId();
        List<Role> roleList = roleService.findAll("1");
        request.setAttribute("roleList",roleList);
        return "system/role/role-add";
    }

    @RequestMapping("/edit")
    public String edit(Role role){
        String companyId=getLoginCompanyId();
        String companyName=getLoginCompanyName();
        role.setCompanyId(companyId);
        role.setCompanyName(companyName);

        if (StringUtils.isEmpty(role.getId())){
            roleService.save(role);
        }else {
            roleService.update(role);
        }
        return "redirect:/system/role/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){

        Role role = roleService.findById(id);

        List<Role> roleList = roleService.findAll("1");

        request.setAttribute("role",role);
        request.setAttribute("roleList",roleList);
        return "system/role/role-update";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        roleService.delete(id);
        return "redirect:/system/role/list";
    }

    @RequestMapping("/roleModule")
    public String roleModule(String roleid){

        Role role = roleService.findById(roleid);

        request.setAttribute("role",role);

        return "system/role/role-module";
    }


}
