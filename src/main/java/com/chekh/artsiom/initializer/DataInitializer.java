package com.chekh.artsiom.initializer;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private DepartmentInitializer departmentInitializer;

    @Autowired
    private StudentInitializer studentInitializer;

    @Autowired
    private TeacherInitializer teacherInitializer;

    @Autowired
    private SubjectInitializer subjectInitializer;

    @Autowired
    private ScheduleInitializer scheduleInitializer;

    @Autowired
    private DepartmentStudentInitializer departmentStudentInitializer;

    @Autowired
    private StudentSubjectInitializer studentSubjectInitializer;

    @Autowired
    private TeacherSubjectInitializer teacherSubjectInitializer;

    @PostConstruct
    public void initialize() {
        departmentInitializer.initialize();
        studentInitializer.initialize();
        teacherInitializer.initialize();
        subjectInitializer.initialize();
        studentInitializer.initialize();
        departmentStudentInitializer.initialize();
        studentSubjectInitializer.initialize();
        teacherSubjectInitializer.initialize();
    }
}
