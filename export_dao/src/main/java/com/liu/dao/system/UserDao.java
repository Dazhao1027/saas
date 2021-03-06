package com.liu.dao.system;


import com.liu.domain.system.User;

import java.util.List;


public interface UserDao {

	//根据企业id查询全部
	List<User> findAll(String companyId);

	//根据id查询
    User findById(String userId);

	//根据id删除
	void delete(String userId);

	//保存
	void save(User user);

	//更新
	void update(User user);

    Long findUserRoleByUserId(String id);

    void deleteUserRoleByUserId(String userId);

	void saveUserRole(String userId, String roleId);

    User findByEmail(String email);
}