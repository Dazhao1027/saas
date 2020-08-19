package com.liu.web.controller.company;

import com.liu.domain.company.Company;
import com.liu.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Company> list = companyService.findAll();
        model.addAttribute("list", list);

        return "company/company-list";
    }

//
//    @RequestMapping("/test")
//    public String test(Date b){
//        System.out.println(b);
//        return "success";
//    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "company/company-add";
    }

    @RequestMapping("/edit")
    public String edit(Company company){

        if (StringUtils.isEmpty(company.getId())){
            companyService.save(company);
        }else {
            companyService.update(company);
        }
        return "redirect:/company/list";
    }


    @RequestMapping("/toUpdate")
    public String toUpdate(String id,Model model){
        Company company=companyService.findById(id);
        model.addAttribute("company",company);
        return "company/company-update";

    }

    @RequestMapping("/delete")
    public String delete(String id){
        companyService.delete(id);

        return "redirect:/company/list";
    }
}
