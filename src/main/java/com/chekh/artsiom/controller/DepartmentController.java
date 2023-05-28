package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.service.DepartmentServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


class DepartmentController {

  @Autowired
  private DepartmentServiceImpl departmentService;

  @GetMapping
  public List<Department> getAllDepartments() {
    return departmentService.getAllDepartments();
  }


}
