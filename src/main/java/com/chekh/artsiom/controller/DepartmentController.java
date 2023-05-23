package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.service.DepartmentServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
class DepartmentController {

  @Autowired
  private DepartmentServiceImpl departmentService;

  @GetMapping
  public List<Department> getAllDepartments() {
    return departmentService.getAllDepartments();
  }


}
