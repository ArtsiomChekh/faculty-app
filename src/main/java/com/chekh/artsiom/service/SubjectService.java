package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Subject;
import java.util.List;

public interface SubjectService {

  List<Subject> getAllSubjects();

  List<Subject> getSubjectsByDepartment(Long departmentId);

  Subject addSubject(Subject subject);

  List<Subject> getSubjectsByTeacher(Long teacherId);

  List<Subject> findAll();

}
