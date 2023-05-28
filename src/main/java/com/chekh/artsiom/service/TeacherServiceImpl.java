package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired
  private SubjectRepository subjectRepository;


  // список всех преподавателей
  @Override
  public List<Teacher> findAll() {
    return teacherRepository.findAll();
  }

  //  список преподавателей по каждой кафедре
  @Override
  public List<Teacher> findByDepartmentId(Long departmentId) {
    return teacherRepository.findAllByDepartmentId(departmentId);
  }

  // список преподавателей по каждому предмету
  @Override
  public List<Teacher> findBySubjectId(Long subjectId) {
    return teacherRepository.findAllBySubjects_Id(subjectId);
  }




  // добавить преподавателя на кафедру
  @Override
  public Teacher addTeacher(Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  // преподователь: список предметов своей кафедры
//  @Override
//  public Set<Subject> getDepartmentSubjects(Teacher teacher) {
//    Set<Subject> departmentSubjects = new HashSet<>();
//    Department department = teacher.getDepartment();
//
//    if (department != null) {
//      Set<Subject> subjects = department.getSubjects();
//      for (Subject subject : subjects) {
//        if (subject.getTeacher().equals(teacher)) {
//          departmentSubjects.add(subject);
//        }
//      }
//    }
//
//    return departmentSubjects;
//  }

  // преподователь: список преподавателей своей кафедры
  @Override
  public List<Teacher> findAllByDepartment(Teacher teacher) {
    List<Teacher> teachers = teacherRepository.findAllByDepartmentId(teacher.getDepartment().getId());
    // упорядочиваем список преподавателей по фамилии
    teachers.sort(Comparator.comparing(Teacher::getLastName));
    return teachers;
  }

  // Преподаватель может * взяться вести предмет кафедры


}
