package com.chekh.artsiom.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TeacherTest {

  private Teacher teacher;
  private Department department;
  private List<Subject> subjects;

  @Before
  public void setUp() {
    department = new Department("Department of Math", "description");
    teacher = new Teacher("Petr", "Petrov", department);
    subjects = new ArrayList<>();
    Subject subject1 = new Subject("Algebra", department);
    Subject subject2 = new Subject("Calculus", department);
    subjects.add(subject1);
    subjects.add(subject2);
    teacher.setSubjects(subjects);
  }

  @Test
  public void testGetId() {
    Long id = 1L;
    teacher.setId(id);
    assertEquals(id, teacher.getId());
  }

  @Test
  public void testGetFirstName() {
    assertEquals("Petr", teacher.getFirstName());
  }

  @Test
  public void testSetFirstName() {
    String newName = "Igor";
    teacher.setFirstName(newName);
    assertEquals(newName, teacher.getFirstName());
  }

  @Test
  public void testGetLastName() {
    assertEquals("Petrov", teacher.getLastName());
  }

  @Test
  public void testSetLastName() {
    String newName = "Bug";
    teacher.setLastName(newName);
    assertEquals(newName, teacher.getLastName());
  }

  @Test
  public void testGetDepartment() {
    assertEquals(department, teacher.getDepartment());
  }

  @Test
  public void testSetDepartment() {
    Department newDepartment = new Department("Physics", "description");
    teacher.setDepartment(newDepartment);
    assertEquals(newDepartment, teacher.getDepartment());
  }

  @Test
  public void testGetSubjects() {
    assertEquals(subjects, teacher.getSubjects());
  }

  @Test
  public void testSetSubjects() {
    List<Subject> newSubjects = new ArrayList<>();
    Subject subject3 = new Subject("Geometry", department);
    newSubjects.add(subject3);
    teacher.setSubjects(newSubjects);
    assertEquals(newSubjects, teacher.getSubjects());
  }

  @Test
  public void testAddSubject() {
    Subject subject3 = new Subject("Geometry", new Department());
    teacher.addSubject(subject3);
    assertTrue(teacher.getSubjects().contains(subject3));
    assertTrue(subject3.getTeachers().contains(teacher));
  }

  @Test
  public void testConstructor() {
    Teacher newTeacher = new Teacher();
    assertNotNull(newTeacher);
  }
}
