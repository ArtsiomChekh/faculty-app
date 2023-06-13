package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.model.TeacherSubject;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import com.chekh.artsiom.repository.TeacherSubjectRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
    return teacherRepository.findByDepartmentId(departmentId);
  }
  @Override
  public List<Teacher> findBySubjectId(Long subjectId) {
    return teacherRepository.findBySubjectId(subjectId);
  }

  @Override
  public List<Teacher> findByDepartmentIdAndSubjectId(Long departmentId, Long subjectId) {
    return teacherRepository.findByDepartmentIdAndSubjectId(departmentId,subjectId);
  }

  @Override
  public void saveTeacher(Teacher teacher) {
    teacherRepository.save(teacher);
  }

  @Override
  @Transactional
  public void deleteTeacherById(long id) {
    deleteTeacherSubjectsByTeacherId(id);
    teacherRepository.deleteById(id);
  }

  @Override
  public void deleteTeacherSubjectsByTeacherId(long teacherId) {
    teacherSubjectRepository.deleteByTeacherId(teacherId);
  }

  @Override
  public Teacher getTeacherById(long id) {
    return teacherRepository.findById(id).orElse(null);
  }

  @Transactional
  public void saveTeacherWithSubjects(Teacher teacher, List<Long> subjectIds) {

    // Сохраняем преподавателя и получаем его id
    Long teacherId = teacherRepository.save(teacher).getId();

    teacherSubjectRepository.deleteByTeacherId(teacherId);

    // Создаем объекты TeacherSubject для каждого выбранного предмета и связываем их с преподавателем
    if (subjectIds != null && !subjectIds.isEmpty()) {
      for (Long subjectId : subjectIds) {

        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        TeacherSubject teacherSubject = new TeacherSubject(teacher, subject);
        teacherSubjectRepository.save(teacherSubject);
      }
    }
  }

  @Override
  public List<Subject> getSubjectsByTeacherId(Long teacherId) {
    List<TeacherSubject> teacherSubjects = teacherSubjectRepository.findByTeacherId(teacherId);
    List<Subject> subjects = new ArrayList<>();
    for (TeacherSubject teacherSubject : teacherSubjects) {
      subjects.add(teacherSubject.getSubject());
    }
    return subjects;
  }




}
