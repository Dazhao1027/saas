package com.liu.dao.system;

import com.liu.domain.system.Dept;

import java.util.List;

public interface DeptDao {

    List<Dept> findAll(String companyId);

    Dept findById(String deptId);

    void save(Dept dept);

    void update(Dept dept);

    Long findByParent(String id);

    void delete(String id);
}
