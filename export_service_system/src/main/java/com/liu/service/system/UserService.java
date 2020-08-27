package com.liu.service.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.Dept;
import com.liu.domain.system.User;

import java.util.List;

public interface UserService {

    //分页查询
    PageInfo<User> findAll(String companyId, int pageNum, int pageSize);

    //根据企业id查询全部
    List<User> findAll(String companyId);

    //根据id查询
    User findById(String userId);

    //根据id删除
    Boolean delete(String id);

    //保存
    void save(User user);

    //更新
    void update(User user);

    void changeRole(String userId, String[] roleIds);

    User findByEmail(String email);
}
