package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.DepartmentRepository;
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


  // сортировка списка кафедр по количеству преподавателей
    @Override
    public List<Department> getDepartmentsByTeacherCount() {
      return departmentRepository.findAllByOrderByTeacherCountDesc();
  }

  // сортировка списка кафедр по количеству студентов
  @Override
  public List<Department> getDepartmentsByStudentCount() {
    return departmentRepository.findAllByOrderByStudentCountDesc();
  }

  // добавить кафедру
  @Override
  public Department addDepartment(Department department) {
    return departmentRepository.save(department);
  }



}
