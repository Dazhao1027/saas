package com.liu.service.company;


import com.github.pagehelper.PageInfo;
import com.liu.domain.company.Company;

import java.util.List;

public interface CompanyService {

    void update(Company company);

    void save(Company company);

    Company findById(String id);

    void delete(String id);

    List<Company> findAll();

    PageInfo<Company> findByPage(int pageNum,int pageSize);
}
