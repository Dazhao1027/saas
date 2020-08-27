package com.liu.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.dao.system.UserDao;
import com.liu.domain.system.User;
import com.liu.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<User> findAll(String companyId, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);

        List<User> list = userDao.findAll(companyId);

        return new PageInfo<>(list);
    }

    @Override
    public List<User> findAll(String companyId) {
        return userDao.findAll(companyId);
    }

    @Override
    public User findById(String userId) {
        return userDao.findById(userId);
    }

    @Override
    public Boolean delete(String id) {
        // 1. 先查询用户角色中间表
        Long count = userDao.findUserRoleByUserId(id);

        if (count == 0) {
            // 说明当前删除的用户id在用户角色中间表不存在，可以删除
            userDao.delete(id);
            return true;
        }
        return false;

    }

    @Override
    public void save(User user) {
        user.setId(UUID.randomUUID().toString());
        if ("".equals(user.getDeptId())){
            user.setDeptId(null);
        }
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void changeRole(String userId, String[] roleIds) {
        userDao.deleteUserRoleByUserId(userId);

        if (roleIds !=null && roleIds.length>0){
            for (String roleId : roleIds) {
                userDao.saveUserRole(userId,roleId);
            }
        }
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
