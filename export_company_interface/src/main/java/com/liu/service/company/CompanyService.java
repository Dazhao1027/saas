package com.liu.service.company;

import com.github.pagehelper.PageInfo;
import com.liu.domain.company.Company;

import java.util.List;

public interface CompanyService {
    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize 页大小
     * @return 返回PageHelper提供的封装分页参数的PageInfo对象
     */
    PageInfo<Company> findByPage(int pageNum, int pageSize);

    /**
     * 查询列表
     */
    List<Company> findAll();

    /**
     * 添加
     * @param company
     */
    void save(Company company);

    /**
     * 修改
     * @param company
     */
    void update(Company company);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Company findById(String id);

    /**
     * 删除
     * @param id
     */
    void delete(String id);
}
