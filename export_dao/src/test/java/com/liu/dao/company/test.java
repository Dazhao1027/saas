package com.liu.dao.company;

import com.liu.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class test {
    @Autowired
    private CompanyDao companyDao;

    @Test
    public void test1() {
        System.out.println(companyDao.findAll());
    }

    @Test
    public void test2(){
        Company company = new Company();
        company.setId("2");
        company.setName("sda");
        company.setAddress("sh");

        companyDao.save(company);
    }
}
