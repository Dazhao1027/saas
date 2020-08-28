package com.liu.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.liu.domain.cargo.*;
import com.liu.service.cargo.ContractProductService;
import com.liu.service.cargo.FactoryService;
import com.liu.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cargo/contractProduct")
public class contractProductController extends BaseController {

    @Reference
    private ContractProductService contractProductService;
    @Reference
    private FactoryService factoryService;

    /**
     * 1. 列表
     * http://localhost:8080/cargo/contractProduct/list.do?contractId=1
     */
    @RequestMapping("/list")
    public String list(String contractId,
            @RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "5") int pageSize){
        //1. 查询货物的厂家
        FactoryExample factoryExample = new FactoryExample();
        factoryExample.createCriteria().andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);

        //2. 根据购销合同id，查询货物
        ContractProductExample contractProductExample = new ContractProductExample();
        contractProductExample.createCriteria().andContractIdEqualTo(contractId);
        PageInfo<ContractProduct> pageInfo = contractProductService.findByPage(contractProductExample, pageNum, pageSize);


        //3. 保存数据
        request.setAttribute("factoryList",factoryList);
        request.setAttribute("pageInfo",pageInfo);
        request.setAttribute("contractId",contractId);
        return "cargo/product/product-list";
    }

    /**
     * 2. 添加或修改货物
     */
    @RequestMapping("/edit")
    public String edit(ContractProduct contractProduct){
        contractProduct.setCompanyId(getLoginCompanyId());
        contractProduct.setCompanyId(getLoginCompanyName());

        if (StringUtils.isEmpty(contractProduct.getId())){
            contractProductService.save(contractProduct);
        }else {
            contractProductService.update(contractProduct);
        }
        //添加成功，重定向到货物列表，注意要传入contractId购销合同id
        return "redirect:/cargo/contractProduct/list?contractId=" + contractProduct.getContractId();
    }

    /**
     * 3. 修改 进入修改页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        // 根据id查询货物
        ContractProduct contractProduct = contractProductService.findById(id);
        request.setAttribute("contractProduct",contractProduct);

        //查询货物的厂家
        FactoryExample factoryExample = new FactoryExample();
        factoryExample.createCriteria().andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList",factoryList);

        return "cargo/product/product-update";

    }
    /**
     * 4. 删除货物
     * 请求地址：http://localhost:8080/cargo/contractProduct/delete.do
     * 请求参数：
     *      id              货物id，用于执行删除
     *      contractId      购销合同合同id，用于跳转到列表（根据购销合同查询货物）
     */

    @RequestMapping("/delete")
    public String delete(String id,String contractId){
        contractProductService.delete(id);
        return "redirect:/cargo/contractProduct/list?contractId=" + contractId;
    }

}
