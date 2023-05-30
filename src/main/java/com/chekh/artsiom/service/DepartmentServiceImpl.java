package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.DepartmentRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired
  private DepartmentRepository departmentRepository;

  // Returns a list of all departments
  @Override
  public List<Department> getAllDepartments() {
    // Use the department repository to find all departments
    return departmentRepository.findAll();
  }

  // Sorts the list of departments by the number of students, in ascending or descending order based on the isAscending parameter
  @Override
  public List<Department> getAllDepartmentsSortedByStudentCount(boolean isAscending) {
    // Use the department repository to find all departments sorted by student count
    List<Department> departments = departmentRepository.findAllByOrderByStudentCountDesc();

    // Reverse the list if isAscending is false
    if (!isAscending) {
      Collections.reverse(departments);
    }

    return departments;
  }

  // Sorts the list of departments by the number of teachers, in ascending or descending order based on the isAscending parameter
  public List<Department> getAllDepartmentsSortedByTeacherCount(boolean isAscending) {
    // Use the department repository to find all departments sorted by teacher count
    List<Department> departments = departmentRepository.findAllByOrderByTeacherCountDesc();

    // Reverse the list if isAscending is false
    if (!isAscending) {
      Collections.reverse(departments);
    }

    return departments;
  }

  @Override
  public Department getDepartmentById(long id) {
    return departmentRepository.findById(id).orElse(null);
  }

  @Override
  public void saveDepartment(Department department) {
    departmentRepository.save(department);
  }

  @Override
  public void deleteDepartmentById(long id) {
    departmentRepository.deleteById(id);
  }


}
