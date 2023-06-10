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

  @Override
  public List<Subject> getAllSubjects() {
    return subjectRepository.findAll();
  }

  @Override
  public List<Subject> getSubjectsByDepartment(Long departmentId) {
    return subjectRepository.findAllByDepartmentId(departmentId);
  }

//  @Override
//  public List<Subject> getSubjectsByTeacher(Long teacherId) {
//    return subjectRepository.findAllByTeachersId(teacherId);
//  }

  @Override
  public List<Subject> findAll() {
    return subjectRepository.findAll();
  }

  @Override
  public List<Subject> getSubjectsByIds(List<Long> ids) {
    return subjectRepository.findByIdIn(ids);
  }

  @Override
  public void saveSubject(Subject subject) {
    subjectRepository.save(subject);
  }

  @Override
  public void deleteSubjectById(long id) {
    subjectRepository.deleteById(id);
  }

  @Override
  public Subject getSubjectById(long id) {
    return subjectRepository.findById(id).orElse(null);
  }

}


