package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.repository.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

  @Autowired
  private DepartmentRepository departmentRepository;

  public List<Department> getAllDepartments() {
    return departmentRepository.findAll();
  }

  public Department getDepartmentById(Long id) {
    return departmentRepository.findById(id).orElse(null);
  }

  public Department createDepartment(Department department) {
    return departmentRepository.save(department);
  }

  public Department updateDepartment(Long id, Department department) {
    Department existingDepartment = departmentRepository.findById(id).orElse(null);
    if (existingDepartment == null) {
      return null;
    }
    existingDepartment.setName(department.getName());
    return departmentRepository.save(existingDepartment);
  }

  public void deleteDepartment(Long id) {
    departmentRepository.deleteById(id);
  }
}
