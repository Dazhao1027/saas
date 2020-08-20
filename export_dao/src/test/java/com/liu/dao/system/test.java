package com.liu.dao.system;

import com.liu.domain.system.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class test {
    @Autowired
    private DeptDao deptDao;
    @Test
    public void test1(){
        List<Dept> list = deptDao.findAll("1");
        System.out.println(list);
    }

    @Test
    public void test2(){
        System.out.println(deptDao.findById("100101"));
    }
}
