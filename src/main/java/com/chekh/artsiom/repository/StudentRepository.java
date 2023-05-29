package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
 List<Student> findAllBySubjects_Id(Long courseId);

 List<Student> findAllByDepartments_Id(Long departmentId);
 List<Student> findByDepartments_IdAndSubjects_Id(Long departmentId, Long subjectId);

}
