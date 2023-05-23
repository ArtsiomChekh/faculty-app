package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Teacher;
import java.util.List;

public interface DepartmentService {

  List<Department> getAllDepartments();
  List<Department> getDepartmentsByTeacherCount();
  List<Department> getDepartmentsByStudentCount();

  Department addDepartment(Department department);

}
