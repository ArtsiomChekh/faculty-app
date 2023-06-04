package com.chekh.artsiom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.repository.DepartmentRepository;
import com.chekh.artsiom.repository.StudentRepository;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SubjectRepositoryIntegrationTest {

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private DepartmentRepository departmentRepository;


  @Test
  public void testGetSubjectsByDepartmentWhenTableIsNotEmpty() {

    Department department = new Department("Кафедра 1", "описание");
    departmentRepository.save(department);

    Subject subject1 = new Subject("English", department);
    Subject subject2 = new Subject("History", department);
    subjectRepository.save(subject1);
    subjectRepository.save(subject2);

    List<Subject> subjects = subjectRepository.findAllByDepartmentId(department.getId());

    assertEquals(2, subjects.size());
    assertTrue(subjects.contains(subject1));
    assertTrue(subjects.contains(subject2));

    Subject subject3 = new Subject("Math", department);
    Subject subject4 = new Subject("Biology", department);
    subjectRepository.save(subject3);
    subjectRepository.save(subject4);

    subjects = subjectRepository.findAllByDepartmentId(department.getId());

    assertEquals(4, subjects.size());
    assertTrue(subjects.contains(subject1));
    assertTrue(subjects.contains(subject2));
    assertTrue(subjects.contains(subject3));
    assertTrue(subjects.contains(subject4));
  }


}
