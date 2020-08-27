package com.liu.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.Module;
import com.liu.domain.system.Role;
import com.liu.service.system.ModuleService;
import com.liu.service.system.RoleService;
import com.liu.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        String companyId = getLoginCompanyId();
        PageInfo<Role> pageInfo = roleService.findByPage(companyId, pageNum, pageSize);

        request.setAttribute("pageInfo", pageInfo);

        return "system/role/role-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {

        String companyId = getLoginCompanyId();
        List<Role> roleList = roleService.findAll("1");
        request.setAttribute("roleList", roleList);
        return "system/role/role-add";
    }

    @RequestMapping("/edit")
    public String edit(Role role) {
        String companyId = getLoginCompanyId();
        String companyName = getLoginCompanyName();
        role.setCompanyId(companyId);
        role.setCompanyName(companyName);

        if (StringUtils.isEmpty(role.getId())) {
            roleService.save(role);
        } else {
            roleService.update(role);
        }
        return "redirect:/system/role/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id) {

        Role role = roleService.findById(id);

        List<Role> roleList = roleService.findAll("1");

        request.setAttribute("role", role);
        request.setAttribute("roleList", roleList);
        return "system/role/role-update";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        roleService.delete(id);
        return "redirect:/system/role/list";
    }

    @RequestMapping("/roleModule")
    public String roleModule(String roleid) {

        Role role = roleService.findById(roleid);

        request.setAttribute("role", role);

        return "system/role/role-module";
    }

    @RequestMapping("/getZtreeNode")
    @ResponseBody   // 方法返回的对象转json： [{},{}]
    public List<Map<String, Object>> getZtreeNode(String roleId) {
        //1. 返回结果
        List<Map<String, Object>> result = new ArrayList<>();

        //2. 查询所有的权限
        List<Module> list = moduleService.findAll();

        //3. 查询角色已经拥有的权限.为什么？ 页面默认选中
        List<Module> roleModuleList = moduleService.findModuleByRoleId(roleId);

        //4. 遍历权限，封装返回结果
        for (Module module : list) {
            // 创建map，封装权限信息
            Map<String, Object> map = new HashMap<>();
            map.put("id", module.getId());
            map.put("pId", module.getParentId());
            map.put("name", module.getName());
            map.put("open", true);
//             判断
//            if (roleModuleList != null && roleModuleList.size() > 0) {
//                for (Module temp : roleModuleList) {
//                    if (temp.getId().equals(module.getId())) {
//                        map.put("checked", true);
//                    }
//                }
//            }
            // 或者
            if (roleModuleList.contains(module)){ // 注意Module对象要重写equals方法
                map.put("checked",true);
            }
            // map添加到集合
            result.add(map);
        }

            return result;
    }



    @RequestMapping("/updateRoleModule")
    public String updateRoleModule(String roleId,String moduleIds){
        roleService.updateRoleModule(roleId,moduleIds);
        return "redirect:/system/role/list";
    }

}
