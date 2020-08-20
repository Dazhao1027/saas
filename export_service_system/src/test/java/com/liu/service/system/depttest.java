package com.liu.service.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class depttest {

    @Autowired
    private DeptService deptService;

    @Test
    public void test1(){
        System.out.println(deptService.findAll("1", 1, 1));
    }
}
