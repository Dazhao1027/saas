package com.liu.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.dao.cargo.FactoryDao;
import com.liu.domain.cargo.Factory;
import com.liu.domain.cargo.FactoryExample;
import com.liu.service.cargo.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryDao factoryDao;
    @Override
    public PageInfo<Factory> findByPage(FactoryExample factoryExample, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Factory> list = factoryDao.selectByExample(factoryExample);

        return new PageInfo<>(list);
    }

    @Override
    public List<Factory> findAll(FactoryExample factoryExample) {
        return factoryDao.selectByExample(factoryExample);
    }

    @Override
    public Factory findById(String id) {
        return factoryDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Factory factory) {
        factory.setId(UUID.randomUUID().toString());
        factoryDao.insertSelective(factory);
    }

    @Override
    public void update(Factory factory) {
        factoryDao.updateByPrimaryKeySelective(factory);
    }

    @Override
    public void delete(String id) {
        factoryDao.deleteByPrimaryKey(id);
    }
}
