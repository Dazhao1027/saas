package com.liu.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.Dept;
import com.liu.domain.system.Role;
import com.liu.domain.system.User;
import com.liu.service.system.DeptService;
import com.liu.service.system.RoleService;
import com.liu.service.system.UserService;
import com.liu.web.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;

    //分页查询
    @RequestMapping("/list")
//    @RequiresPermissions("用户管理")
    public String list(
            @RequestParam(defaultValue = "1")Integer pageNum,
                       @RequestParam(defaultValue = "5")Integer pageSize){
        //shiro权限校验1 编码方式
        //访问当前用户必须有"用户管理"的权限,否则报错
        SecurityUtils.getSubject().checkPermission("用户管理");

        String companyId=getLoginCompanyId();
        PageInfo<User> pageInfo = userService.findAll(companyId, pageNum, pageSize);
        request.setAttribute("pageInfo",pageInfo);

        return "/system/user/user-list";
    }

    //进入添加
    @RequestMapping("/toAdd")
    public String toAdd(){
        // 根据企业id，查询所有部门，作为部门的下拉列表
        String companyId = getLoginCompanyId();
        List<Dept> deptList = deptService.findAll(companyId);
        request.setAttribute("deptList",deptList);
        return "system/user/user-add";
    }

    @RequestMapping("/edit")
    public String edit(User user){
        // 从当前登陆用户获取企业id、名称(先写死)
        user.setCompanyId(getLoginCompanyId());
        user.setCompanyName(getLoginCompanyName());

        if (StringUtils.isEmpty(user.getId())){
            //id空添加
            userService.save(user);
        }else {
            //id有修改
            userService.update(user);
        }
        return "redirect:/system/user/list";
    }

    //修改页面
    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        //显示要修改的user信息
        User user = userService.findById(id);
        //显示所有部门
        List<Dept> deptList = deptService.findAll(getLoginCompanyId());
        //保存到页面
        request.setAttribute("user",user);
        request.setAttribute("deptList",deptList);

        return "/system/user/user-update";
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id){
        String result="ok";

        Boolean flag = userService.delete(id);
        if (!flag){
            result="error";
        }
        return result;
    }

    @RequestMapping("/roleList")
    public String roleList(String id){
        //1回显用户名
        User user = userService.findById(id);
        //2 查询所有角色
        List<Role> roleList = roleService.findAll(getLoginCompanyId());
        //3 查询用户已经拥有的角色
        List<Role> userRoleList= roleService.findUserRoleByUserId(id);

        //定义一个字符串存储所有用户的角色id。页面不能遍历集合了
        String roleIds="";
        if (userRoleList !=null && userRoleList.size()>0 ){
            for (Role userRole : userRoleList) {
                roleIds+=userRole.getId()+ ",";
            }
        }

        //保存返回页面
        request.setAttribute("user",user);
        request.setAttribute("roleList",roleList);
        request.setAttribute("roleIds",roleIds);

        return "system/user/user-role";
    }

    @RequestMapping("/changeRole")
    public String changeRole(String userId,String[] roleIds){

        userService.changeRole(userId,roleIds);
        return "redirect:/system/user/list";
    }


}
