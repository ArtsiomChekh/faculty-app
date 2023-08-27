package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;

import java.util.List;
import java.util.Map;


public interface DepartmentService {

  List<Department> getAllDepartments();

  Department getDepartmentById(long id);

  void saveDepartment(Department department);

  void deleteDepartmentById(long id);

  List<Object[]> getDepartmentsStudentTeacherCount();

  List<Object[]> sortDepartments(String sortBy);
}
