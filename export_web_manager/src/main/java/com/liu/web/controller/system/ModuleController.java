package com.liu.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.Dept;
import com.liu.domain.system.Module;
import com.liu.domain.system.User;
import com.liu.service.system.ModuleService;
import com.liu.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/system/module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1")Integer pageNum,
            @RequestParam(defaultValue = "5")Integer pageSize) {

        String companyId = getLoginCompanyId();
        PageInfo<Module> pageInfo = moduleService.findByPage(pageNum, pageSize);
        request.setAttribute("pageInfo", pageInfo);

        return "/system/module/module-list";
    }

    //进入添加
    @RequestMapping("/toAdd")
    public String toAdd(){
        // 根据企业id，查询所有部门，作为部门的下拉列表
        String companyId = getLoginCompanyId();
        List<Module> menus = moduleService.findAll();
        request.setAttribute("menus",menus);
        return "system/module/module-add";
    }

    @RequestMapping("/edit")
    public String edit(Module module){
        // 从当前登陆用户获取企业id、名称(先写死)

        if (StringUtils.isEmpty(module.getId())){
            //id空添加
            moduleService.save(module);
        }else {
            //id有修改
            moduleService.update(module);
        }
        return "redirect:/system/module/list";
    }

    //修改页面
    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        //显示要修改的user信息
        Module module = moduleService.findById(id);
        //显示所有部门
        List<Module> menus = moduleService.findAll();
        //保存到页面
        request.setAttribute("module",module);
        request.setAttribute("menus",menus);

        return "/system/module/module-update";
    }
    @RequestMapping("/delete")
    public String delete(String id){
        moduleService.delete(id);
        return "redirect:/system/module/list";
    }
}
