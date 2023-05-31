package com.chekh.artsiom.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;


public class StudentTest {

  private Student student;
  private Set<Subject> subjects;
  private List<Department> departments;

  @Before
  public void setUp() {
    student = new Student();
    subjects = new HashSet<>();
    subjects.add(new Subject("Math"));
    subjects.add(new Subject("Physics"));
    departments = new ArrayList<>();
    departments.add(new Department("Computer Science", "description"));
    departments.add(new Department("Mechanical Engineering", "description"));
  }

  @Test
  public void testGetSubjects() {
    student.setSubjects(subjects);
    assertEquals(subjects, student.getSubjects());
  }

  @Test
  public void testGetDepartments() {
    student.setDepartments(departments);
    assertNotNull(student.getDepartments());
    assertEquals(2, student.getDepartments().size());
  }

  @Test
  public void testSetFirstName() {
    student.setFirstName("John");
    assertEquals("John", student.getFirstName());
  }

  @Test
  public void testSetLastName() {
    student.setLastName("Doe");
    assertEquals("Doe",student.getLastName());
  }

  @Test
  public void testSetDepartments() {
    student.setDepartments(departments);
    assertEquals(departments, student.getDepartments());
  }

  @Test
  public void testSetSubjects() {
    student.setSubjects(subjects);
    assertEquals(subjects, student.getSubjects());
  }
}