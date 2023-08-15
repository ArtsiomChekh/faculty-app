package com.chekh.artsiom.repository;

import com.chekh.artsiom.model.Department;

import java.util.Map;
import javax.persistence.MapKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d, COUNT(ds) FROM Department d JOIN DepartmentStudent ON d.id = ds.dapartment_id GROUP BY d")
    @MapKey(name = "d")
    Map<Department, Long> findDepartmentStudentCount();

}
