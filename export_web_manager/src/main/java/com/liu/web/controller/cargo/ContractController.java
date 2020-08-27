package com.liu.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.liu.domain.cargo.Contract;
import com.liu.domain.cargo.ContractExample;
import com.liu.service.cargo.ContractService;
import com.liu.web.controller.BaseController;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/cargo/contract")
public class ContractController extends BaseController {

    @Reference
    private ContractService contractService;

    /**
     * 1. 列表
     */
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "5") int pageSize){
        //构造查询条件
        ContractExample example = new ContractExample();
        //排序:create_time排序
        example.setOrderByClause("create_time desc");
        ContractExample.Criteria criteria = example.createCriteria();
        //根据company_id查询
        criteria.andCompanyIdEqualTo(getLoginCompanyId());

        //查询
        PageInfo<Contract> pageInfo = contractService.findByPage(example, pageNum, pageSize);
        //保存到页面
        request.setAttribute("pageInfo",pageInfo);
        return "cargo/contract/contract-list";
    }

    /**
     * 2. 添加（1）进入添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd(){

        return "cargo/contract/contract-add";
    }

    /**
     * 2. 添加（2）添加
     */
    @RequestMapping("/edit")
    public String edit(Contract contract){
        contract.setCompanyId(getLoginCompanyId());
        contract.setCompanyName(getLoginCompanyName());
        //判断,有id 添加,没id修改
        if(StringUtils.isEmpty(contract.getId())){
            //记录时间
            contract.setCreateTime(new Date());
            //记录创建人
            contract.setCreateBy(getLoginUser().getId());
            contractService.save(contract);
        }else {
            contractService.update(contract);
        }
        return "redirect:/cargo/contract/list";
    }

    /**
     * 3. 修改 进入修改页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Contract contract = contractService.findById(id);
        request.setAttribute("contract",contract);

        return "cargo/contract/contract-update";

    }

    /**
     * 4. 删除
     */
    @RequestMapping("/delete")
    public String delete(String id){
        contractService.delete(id);
        return "redirect:/cargo/contract/list";
    }

    /**
     * 5. 提交：把购销合同状态改为1
     * http://localhost:8080/cargo/contract/submit.do?id=1
     */
    @RequestMapping("/submit")
    public String submit(String id){
        //修改购销合同状态
        Contract contract = new Contract();
        //修改条件
        contract.setId(id);
        //修改字段
        contract.setState(1);
        //修改
        contractService.update(contract);
        return "redirect:/cargo/contract/list";
    }

    /**
     * 5. 取消：把购销合同状态改为0
     * http://localhost:8080/cargo/contract/cancel.do?id=1
     */
    @RequestMapping("/cancel")
    public String cancel(String id){
        //修改购销合同状态
        Contract contract = new Contract();
        //修改条件
        contract.setId(id);
        //修改字段
        contract.setState(0);
        //修改
        contractService.update(contract);
        return "redirect:/cargo/contract/list";
    }

    /**
     * 5. 查看
     * http://localhost:8080/cargo/contract/toView.do?id=1
     */
    @RequestMapping("/toView")
    public String toView(String id){
        Contract contract = contractService.findById(id);

        request.setAttribute("contract",contract);
        return "cargo/contract/contract-view";
    }
}
