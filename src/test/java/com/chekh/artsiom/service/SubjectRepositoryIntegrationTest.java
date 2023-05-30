package com.chekh.artsiom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.repository.DepartmentRepository;
import com.chekh.artsiom.repository.SubjectRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// Use the @RunWith(SpringRunner.class) and @SpringBootTest annotations to run the test using the Spring Boot framework.
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // Use the @Transactional annotation to automatically roll back changes to the database after the test has run.
public class SubjectRepositoryIntegrationTest {

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private  SubjectService subjectService;
  @Autowired
  private DepartmentRepository departmentRepository;


  @Test
  public void testGetSubjectsByDepartmentWhenTableIsNotEmpty() {
    // Setup
    Department department = new Department("Кафедра 1", 1, 2, "описание");
    departmentRepository.save(department);

    Subject subject1 = new Subject("English", department);
    Subject subject2 = new Subject("History", department);
    subjectRepository.save(subject1);
    subjectRepository.save(subject2);

    // Execution
    List<Subject> subjects = subjectRepository.findAllByDepartmentId(department.getId());

    // Verification
    assertEquals(2, subjects.size());
    assertTrue(subjects.contains(subject1));
    assertTrue(subjects.contains(subject2));

    // Setup additional data
    Subject subject3 = new Subject("Math", department);
    Subject subject4 = new Subject("Biology", department);
    subjectRepository.save(subject3);
    subjectRepository.save(subject4);

    // Execution
    subjects = subjectRepository.findAllByDepartmentId(department.getId());

    // Verification
    assertEquals(4, subjects.size());
    assertTrue(subjects.contains(subject1));
    assertTrue(subjects.contains(subject2));
    assertTrue(subjects.contains(subject3));
    assertTrue(subjects.contains(subject4));
  }
}
