package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.repository.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired
  private DepartmentRepository departmentRepository;

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
  public List<Department> getDepartmentsByIds(List<Long> ids) {
    return departmentRepository.findByIdIn(ids);
  }


}



