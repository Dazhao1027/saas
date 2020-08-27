package com.liu.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.dao.system.ModuleDao;
import com.liu.dao.system.RoleDao;
import com.liu.dao.system.UserDao;
import com.liu.domain.system.Module;
import com.liu.domain.system.Role;
import com.liu.domain.system.User;
import com.liu.service.system.ModuleService;
import com.liu.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<Module> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Module> list = moduleDao.findAll();
        return new PageInfo<>(list);    }

    @Override
    public Module findById(String id) {
        return moduleDao.findById(id);
    }

    @Override
    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    @Override
    public void delete(String id) {
        moduleDao.delete(id);
    }

    @Override
    public void save(Module module) {
        module.setId(UUID.randomUUID().toString());
        moduleDao.save(module);
    }

    @Override
    public void update(Module module) {
        moduleDao.update(module);
    }

    @Override
    public List<Module> findModuleByRoleId(String roleId) {

        return moduleDao.findModuleByRoleId(roleId);
    }


    /**
     * 根据登录的用户id查询用户所具有的所有权限（模块，菜单）
     *   1.根据用户id查询用户
     *   2.根据用户degree级别判断
     *   3.如果degree==0 （内部的sass管理）
     *      根据模块中的belong字段进行查询，belong = "0";
     *   4.如果degree==1 （租用企业的管理员）
     *      根据模块中的belong字段进行查询，belong = "1";
     *   5.其他的用户类型
     *      借助RBAC的数据库模型，多表联合查询出结果
     */
    @Override
    public List<Module> findModuleByUserId(String userId) {
        User user = userDao.findById(userId);

        if (user.getDegree()==0){
            return moduleDao.findByBelong("0");
        }else if (user.getDegree()==1){
            return moduleDao.findByBelong("1");
        }else {
        return moduleDao.findModuleByUserId(userId);
        }
    }
}
