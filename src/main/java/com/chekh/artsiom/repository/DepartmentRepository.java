package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Department;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("Select d, count(ds.student) \n"
        + "From Department d\n"
        + "Left Join DepartmentStudent ds ON d.id = ds.department\n"
        + "Left Join Student s ON s.id = ds.student\n"
        + "GROUP BY d")
    List<Object[]> findDepartmentStudentCount();

    // MYSQL
    // SELECT d.id, d.name, COUNT(t.id) AS teacherCount
    //FROM department d
    //LEFT JOIN teacher t ON d.id = t.department_id
    //GROUP BY d.id, d.name
    @Query("Select d, count(t.id) \n"
            + "From Department d\n"
            + "Left Join Teacher t ON d.id = t.department\n"
            + "GROUP BY d")
    List<Object[]> findDepartmentTeacherCount();

}
