package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.TeacherSubject;
import com.chekh.artsiom.model.TeacherSubjectId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, TeacherSubjectId> {

  List<TeacherSubject> findByTeacherId(Long teacherId);

  void deleteByTeacherId(long teacherId);

  TeacherSubject findByTeacherIdAndSubjectId(Long teacherId, Long subjectId);

  List<TeacherSubject> findBySubjectId(Long subjectId);




}
