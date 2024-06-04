package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.DepartmentStudent;
import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.repository.DepartmentRepository;
import com.chekh.artsiom.repository.DepartmentStudentRepository;
import com.chekh.artsiom.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentStudentInitializer {

    @Autowired
    private DepartmentStudentRepository departmentStudentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentRepository studentRepository;


    @PostConstruct
    public void initialize() {
        if (departmentStudentRepository.count() < 3) {
            List<Department> departments = departmentRepository.findAll();
            List<Student> students = studentRepository.findAll();

            DepartmentStudent departmentStudent1 = new DepartmentStudent(departments.get(0), students.get(0));
            departmentStudentRepository.save(departmentStudent1);

            DepartmentStudent departmentStudent2 = new DepartmentStudent(departments.get(1), students.get(1));
            departmentStudentRepository.save(departmentStudent2);

            DepartmentStudent departmentStudent3 = new DepartmentStudent(departments.get(2), students.get(2));
            departmentStudentRepository.save(departmentStudent3);
        }
    }
}
