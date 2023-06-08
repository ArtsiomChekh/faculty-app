package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.model.TeacherSubject;
import com.chekh.artsiom.model.TeacherSubjectId;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import com.chekh.artsiom.repository.TeacherSubjectRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private TeacherSubjectRepository teacherSubjectRepository;

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
    return teacherRepository.findAllBySubjectId(subjectId);
  }

  @Override
  public List<Teacher> findByDepartmentIdAndSubjectId(Long departmentId, Long subjectId) {
    return teacherRepository.findByDepartmentIdAndSubjectId(departmentId, subjectId);
  }

  @Override
  public void saveTeacher(Teacher teacher) {
     teacherRepository.save(teacher);
  }

  @Transactional
  public Long saveTeacherWithSubjects(Teacher teacher, List<Long> subjectIds, Department department) {
    // Связываем преподавателя с кафедрой
    teacher.setDepartment(department);

    // Сохраняем преподавателя и получаем его id
    Long teacherId = teacherRepository.save(teacher).getId();

    // Создаем объекты TeacherSubject для каждого выбранного предмета и связываем их с преподавателем
    if (subjectIds != null && !subjectIds.isEmpty()) {
      for (Long subjectId : subjectIds) {
        Subject subject = subjectService.getSubjectById(subjectId);
        TeacherSubject teacherSubject = new TeacherSubject(teacher, subject);
        teacherSubjectRepository.save(teacherSubject);
      }
    }

    return teacherId;
  }

  @Override
  public void deleteTeacherById(long id) {
    teacherRepository.deleteById(id);
  }

  @Override
  public Teacher getTeacherById(long id) {
    return teacherRepository.findById(id).orElse(null);
  }

  @Override
  public List<Subject> getTeacherSubjects(Teacher teacher) {
    return teacherRepository.getTeacherSubjects(teacher);
  }

  @Override
  public void addTeacherSubject(Teacher teacher, Subject subject) {
    List<Subject> teacherSubjects = teacherRepository.getTeacherSubjects(teacher);
    if (!teacherSubjects.contains(subject)) {
      TeacherSubject teacherSubject = new TeacherSubject(teacher, subject);
      teacherSubjectRepository.save(teacherSubject);
    }
  }



}
