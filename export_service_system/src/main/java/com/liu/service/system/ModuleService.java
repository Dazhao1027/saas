package com.liu.service.system;


import com.github.pagehelper.PageInfo;
import com.liu.domain.system.Module;

import java.util.List;

public interface ModuleService {

    //分页查询
    PageInfo<Module> findByPage(int pageNum, int pageSize);

    //根据id查询
    Module findById(String id);

    //查询全部
    List<Module> findAll();

    //根据id删除
    void delete(String id);

    //添加
    void save(Module module);

    //更新
    void update(Module module);

    /**
     * 查询角色已经拥有的权限
     * @param roleId
     * @return
     */
    List<Module> findModuleByRoleId(String roleId);

    List<Module> findModuleByUserId(String userId);
}
