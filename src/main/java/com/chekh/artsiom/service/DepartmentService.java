package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Teacher;
import java.util.List;

public interface DepartmentService {

  List<Department> getAllDepartments();
  List<Department> getAllDepartmentsSortedByStudentCount(boolean isAscending);
  List<Department> getAllDepartmentsSortedByTeacherCount(boolean isAscending);

  Department getDepartmentById(long id);

  void saveDepartment(Department department);

  void deleteDepartmentById(long id);


}
