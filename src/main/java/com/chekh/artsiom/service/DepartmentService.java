package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import java.util.List;


public interface DepartmentService {

  List<Department> getAllDepartments();

  Department getDepartmentById(long id);

  void saveDepartment(Department department);

  void deleteDepartmentById(long id);

  List<Department> getDepartmentsByIds(List<Long> ids);




}
