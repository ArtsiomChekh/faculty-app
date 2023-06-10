package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.TeacherSubject;
import com.chekh.artsiom.model.TeacherSubjectId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, TeacherSubjectId> {

  List<TeacherSubject> findByTeacherId(Long teacherId);

  TeacherSubject findByTeacherIdAndSubjectId(Long teacherId, Long subjectId);

  List<TeacherSubject> findBySubjectId(Long subjectId);




}
