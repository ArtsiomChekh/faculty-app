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

  // Returns a list of all subjects
  @Override
  public List<Subject> getAllSubjects() {
    return subjectRepository.findAll();
  }

  // Returns a list of subjects for a specified department
  @Override
  public List<Subject> getSubjectsByDepartment(Long departmentId) {
    return subjectRepository.findAllByDepartmentId(departmentId);
  }

  // Returns a list of subjects taught by a specified teacher
  @Override
  public List<Subject> getSubjectsByTeacher(Long teacherId) {
    return subjectRepository.findAllByTeachersId(teacherId);
  }

  // Returns a list of all subjects
  @Override
  public List<Subject> findAll() {
    return subjectRepository.findAll();
  }

  // Returns a list of subjects with the specified IDs
  @Override
  public List<Subject> getSubjectsByIds(List<Long> ids) {
    // Use the subject repository to find all subjects with the specified IDs
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

  // Returns the subject with the specified ID
  @Override
  public Subject getSubjectById(long id) {
    return subjectRepository.findById(id).orElse(null);
  }

}
