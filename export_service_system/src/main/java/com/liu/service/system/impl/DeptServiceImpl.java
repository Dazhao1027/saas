package com.liu.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.dao.system.DeptDao;
import com.liu.domain.system.Dept;
import com.liu.service.system.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public PageInfo<Dept> findAll(String companyId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<Dept> list = deptDao.findAll(companyId);
        return new PageInfo<>(list);
    }

    @Override
    public List<Dept> findAll(String companyId) {
        return deptDao.findAll(companyId);
    }

    @Override
    public Dept findById(String deptId) {
        return deptDao.findById(deptId);
    }

    @Override
    public void save(Dept dept) {
        dept.setId(UUID.randomUUID().toString());
        if (dept.getParent()!=null && "".equals(dept.getParent().getId())){
            dept.getParent().setId(null);
        }
        deptDao.save(dept);
    }
    @Override
    public void update(Dept dept) {
        deptDao.update(dept);
    }

    @Override
    public boolean delete(String id) {
        Long count = deptDao.findByParent(id);

        if (count==0){
            deptDao.delete(id);
            return true;
        }
        return false;
    }
}
