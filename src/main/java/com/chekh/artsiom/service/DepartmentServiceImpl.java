package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.repository.DepartmentRepository;

import java.util.*;

import com.chekh.artsiom.repository.DepartmentStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentStudentRepository departmentStudentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Object[]> getDepartmentsStudentTeacherCount() {
        return departmentRepository.findDepartmentStudentTeacherCount();
    }

    @Override
    public List<Object[]> sortDepartments(String sortBy) {
        List<Object[]> departments = departmentRepository.findDepartmentStudentTeacherCount();

        departments.sort((result1, result2) -> {
            Long count1;
            Long count2;

            if ("studentCount".equals(sortBy)) {
                count1 = (Long) result1[2];
                count2 = (Long) result2[2];
            } else if ("teacherCount".equals(sortBy)) {
                count1 = (Long) result1[3];
                count2 = (Long) result2[3];
            } else {
                throw new IllegalArgumentException("Invalid sortBy parameter: " + sortBy);
            }

            return count2.compareTo(count1);
        });

        return departments;
    }
}



