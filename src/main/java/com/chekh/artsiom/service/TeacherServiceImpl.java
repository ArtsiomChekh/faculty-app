package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

  @Autowired
  private TeacherRepository teacherRepository;

  @Override
  public List<Teacher> getAllTeachers() {
    return teacherRepository.findAll();
  }

  @Override
  public List<Teacher> findByDepartmentId(Long departmentId) {
    return teacherRepository.findAllByDepartmentId(departmentId);
  }

  @Override
  public List<Teacher> findBySubjectId(Long subjectId) {
    return teacherRepository.findAllBySubjects_Id(subjectId);
  }

  @Override
  public List<Teacher> findByDepartmentIdAndSubjectsId(Long departmentId, Long subjectId) {
    return teacherRepository.findByDepartmentIdAndSubjectsId(departmentId, subjectId);
  }

  @Override
  public void saveTeacher(Teacher teacher) {
    teacherRepository.save(teacher);
  }

  @Override
  public void deleteTeacherById(long id) {
    teacherRepository.deleteById(id);
  }

  @Override
  public Teacher getTeacherById(long id) {
    return teacherRepository.findById(id).orElse(null);
  }

}
