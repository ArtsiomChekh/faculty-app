package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.model.TeacherSubject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
  List<TeacherSubject> findByTeacher(Teacher teacher);
  List<TeacherSubject> findBySubject(Subject subject);
  void deleteByTeacherAndSubject(Teacher teacher, Subject subject);
}
