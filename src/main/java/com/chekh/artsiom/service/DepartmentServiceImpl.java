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


  // возвращает список всех кафедр
  @Override
  public List<Department> getAllDepartments() {
    return departmentRepository.findAll();
  }

  // сортировка списка кафедр по количеству студентов
  @Override
  public List<Department> getAllDepartmentsSortedByStudentCount(boolean isAscending) {
    List<Department> departments = departmentRepository.findAllByOrderByStudentCountDesc();
    if (!isAscending) {
      Collections.reverse(departments);
    }
    return departments;
  }


  // сортировка списка кафедр по количеству преподавателей

  public List<Department> getAllDepartmentsSortedByTeacherCount(boolean isAscending) {
    List<Department> departments = departmentRepository.findAllByOrderByTeacherCountDesc();
    if (!isAscending) {
      Collections.reverse(departments);
    }
    return departments;
  }

  // добавить кафедру
  @Override
  public Department addDepartment(Department department) {
    return departmentRepository.save(department);
  }

  @Override
  public Department getDepartmentById(long id) {
    return departmentRepository.findById(id).orElse(null);
  }

  @Override
  public void updateDepartment(Department department) {
    departmentRepository.save(department);
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
