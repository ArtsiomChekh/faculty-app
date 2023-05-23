package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.repository.SubjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

  @Autowired
  SubjectRepository subjectRepository;

  // список всех предметов
  @Override
  public List<Subject> getAllSubjects() {
    return subjectRepository.findAll();
  }

  // список предметов по каждой кафедре
  @Override
  public List<Subject> getSubjectsByDepartment(Long departmentId) {
    return subjectRepository.findAllByDepartmentId(departmentId);
  }

  // добавить предмет для кафедру
  @Override
  public Subject addSubject(Subject subject) {
    return subjectRepository.save(subject);
  }

  // преподователь: список своих предметов
  @Override
  public List<Subject> getSubjectsByTeacher(Long teacherId) {
    return subjectRepository.findAllByTeacherId(teacherId);
  }

  // список предметов
  @Override
  public List<Subject> findAll() {
    return subjectRepository.findAll();
  }
}
