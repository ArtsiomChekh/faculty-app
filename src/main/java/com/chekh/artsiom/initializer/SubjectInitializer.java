package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.repository.DepartmentRepository;
import com.chekh.artsiom.repository.SubjectRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectInitializer {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    public void initialize() {
        if (subjectRepository.count() < 3) {
            List<Department> departmentList = departmentRepository.findAll();

            Subject subject1 = new Subject();
            subject1.setName("Вычислительные машины, системы и сети");
            subject1.setDepartment(departmentList.get(0));
            subjectRepository.save(subject1);

            Subject subject2 = new Subject();
            subject2.setName("Информационные системы и технологии (в экономике)");
            subject2.setDepartment(departmentList.get(1));
            subjectRepository.save(subject2);

            Subject subject3 = new Subject();
            subject3.setName("Информационная безопасность");
            subject3.setDepartment(departmentList.get(2));
            subjectRepository.save(subject3);
        }
    }
}
