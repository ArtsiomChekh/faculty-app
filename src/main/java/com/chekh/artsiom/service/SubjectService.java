package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Subject;
import java.util.List;

public interface SubjectService {
  List<Subject> getAllSubjects();

  List<Subject> findByDepartmentId(Long departmentId);

  List<Subject> findAll();

  List<Subject> getSubjectsByIds(List<Long> asList);

  void saveSubject(Subject subject);

  void deleteSubjectById(long id);

  Subject getSubjectById(long id);
}
