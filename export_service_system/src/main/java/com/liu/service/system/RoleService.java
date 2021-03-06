package com.liu.service.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.Role;

import java.util.List;

public interface RoleService {

    //分页查询
    PageInfo<Role> findByPage(String companyId, int pageNum, int pageSize);

    //根据id查询
    Role findById(String id);

    //查询全部
    List<Role> findAll(String companyId);

    //根据id删除
    void delete(String id);

    //添加
    void save(Role role);

    //更新
    void update(Role role);

    void updateRoleModule(String roleId, String moduleIds);

    List<Role> findUserRoleByUserId(String id);
}
