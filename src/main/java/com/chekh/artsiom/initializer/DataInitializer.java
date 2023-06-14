package com.chekh.artsiom.initializer;

import javax.annotation.PostConstruct;
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

  @PostConstruct
  public void initialize() {
    departmentInitializer.initialize();
    studentInitializer.initialize();
    teacherInitializer.initialize();
    subjectInitializer.initialize();
  }
}
