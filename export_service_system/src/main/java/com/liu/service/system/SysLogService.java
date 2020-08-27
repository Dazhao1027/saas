package com.liu.service.system;

import com.github.pagehelper.PageInfo;
import com.liu.domain.system.SysLog;

public interface SysLogService {

    //分页查询
    PageInfo<SysLog> findByPage(String companyId, int pageNum, int pageSize);

    //保存
    void save(SysLog sysLog);
}
