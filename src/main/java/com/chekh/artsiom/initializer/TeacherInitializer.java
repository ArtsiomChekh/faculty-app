package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.DepartmentRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherInitializer {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    public void initialize() {
        if (teacherRepository.count() < 3) {
            List<Department> departments = departmentRepository.findAll();

            Teacher teacher1 = new Teacher();
            teacher1.setFirstName("Евгений");
            teacher1.setLastName("Болан");
            teacher1.setDepartment(departments.get(0));
            teacherRepository.save(teacher1);

            Teacher teacher2 = new Teacher();
            teacher2.setFirstName("Сергей");
            teacher2.setLastName("Сомов");
            teacher2.setDepartment(departments.get(1));
            teacherRepository.save(teacher2);

            Teacher teacher3 = new Teacher();
            teacher3.setFirstName("Геннадий");
            teacher3.setLastName("Никуль");
            teacher3.setDepartment(departments.get(2));
            teacherRepository.save(teacher3);
        }
    }

}
