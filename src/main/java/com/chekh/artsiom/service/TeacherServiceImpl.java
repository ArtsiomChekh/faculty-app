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

  // Returns a list of all teachers
  @Override
  public List<Teacher> getAllTeachers() {
    return teacherRepository.findAll();
  }

  // Returns a list of teachers for a specified department
  @Override
  public List<Teacher> findByDepartmentId(Long departmentId) {
    return teacherRepository.findAllByDepartmentId(departmentId);
  }

  // Returns a list of teachers for a specified subject
  @Override
  public List<Teacher> findBySubjectId(Long subjectId) {
    return teacherRepository.findAllBySubjects_Id(subjectId);
  }

  // Returns a list of teachers for a specified department and subject
  @Override
  public List<Teacher> findByDepartmentIdAndSubjectsId(Long departmentId, Long subjectId) {
    return teacherRepository.findByDepartmentIdAndSubjectsId(departmentId, subjectId);
  }

  // Returns a list of teachers belonging to the same department as the specified teacher, sorted by last name
  @Override
  public List<Teacher> findAllByDepartment(Teacher teacher) {
    List<Teacher> teachers = teacherRepository.findAllByDepartmentId(
        teacher.getDepartment().getId());
    // Sort the list of teachers by last name
    teachers.sort(Comparator.comparing(Teacher::getLastName));
    return teachers;
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
