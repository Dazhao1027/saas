package com.liu.dao.company;

import com.liu.domain.company.Company;

import java.util.List;

public interface CompanyDao {

    List<Company> findAll();

    void update(Company company);

    void save(Company company);

    Company findById(String id);

    void delete(String id);
}
