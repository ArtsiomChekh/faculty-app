package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import java.util.List;


public interface DepartmentService {

  List<Department> getAllDepartments();

  Department getDepartmentById(long id);

  void saveDepartment(Department department);

  void deleteDepartmentById(long id);




}
