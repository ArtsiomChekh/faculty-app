package com.chekh.artsiom.controller;

import com.chekh.artsiom.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = {"/", "/home"})
    public String showHomePage(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "index";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "accessDeniedPage";
    }
}
