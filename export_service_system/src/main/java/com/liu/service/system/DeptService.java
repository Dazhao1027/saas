package com.liu.service.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.Dept;

import java.util.List;

public interface DeptService {

    PageInfo<Dept> findAll(String companyId,int pageNum,int pageSize);

    List<Dept> findAll(String companyId);

    Dept findById(String deptId);

    void save(Dept dept);

    void update(Dept dept);

    boolean delete(String id);
}
