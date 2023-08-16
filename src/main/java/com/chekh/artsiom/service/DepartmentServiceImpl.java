package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.repository.DepartmentRepository;

import java.util.*;

import com.chekh.artsiom.repository.DepartmentStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private DepartmentStudentRepository departmentStudentRepository;

  @Override
  public List<Department> getAllDepartments() {
    return departmentRepository.findAll();
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

  @Override
  public Map<Department, Long> getDepartmentsStudentCount() {
    List<Object[]> result = departmentRepository.findDepartmentStudentCount();
    Map<Department, Long> departmentStudentCountMap = new HashMap<>();
    for (Object[] row : result) {
      Department department = (Department) row[0];
      Long studentCount = (Long) row[1];
      departmentStudentCountMap.put(department, studentCount);
    }
    return departmentStudentCountMap;
  }

  @Override
  public List<Department> getAllDepartmentsSortedByTeachers() {
    return null;
  }


}



