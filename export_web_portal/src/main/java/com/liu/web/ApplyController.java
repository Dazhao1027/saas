package com.liu.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.liu.domain.company.Company;
import com.liu.service.company.CompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplyController {
    @Reference
    private CompanyService companyService;

    /**
     * 企业入驻，返回json字符串
     * 注意：dubbo服务提供者、消费者通讯，传递的对象一定要实现Serializable接口
     */

    @RequestMapping("/apply1")
    public String apply(Company company){
        try {
            // 远程访问服务
            companyService.save(company);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
}
