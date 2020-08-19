package com.liu.service.company;


import com.liu.domain.company.Company;

import java.util.List;

public interface CompanyService {

    void update(Company company);

    void save(Company company);

    List<Company> findAll();

    Company findById(String id);

    void delete(String id);
}
