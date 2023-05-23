package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
 List<Student> findAllByDepartmentId(Long departmentId);
 List<Student> findAllBySubjects_Id(Long courseId);

}
