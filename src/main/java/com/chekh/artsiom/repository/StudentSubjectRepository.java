package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.StudentSubject;
import com.chekh.artsiom.model.StudentSubjectId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, StudentSubjectId> {

  List<StudentSubject> findByStudentId(Long studentId);

  void deleteByStudentId(long studentId);
}
