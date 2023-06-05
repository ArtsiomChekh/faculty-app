package com.chekh.artsiom.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {

  @Test
  public void testGetFirstName() {
    Student student = new Student("Petr", "Petrov");
    assertEquals("Petr", student.getFirstName());
  }

  @Test
  public void testGetLastName() {
    Student student = new Student("Petr", "Petrov");
    assertEquals("Petrov", student.getLastName());
  }

  @Test
  public void testGetSubjects() {
    Student student = new Student("Petr", "Petrov");
    Set<Subject> subjects = new HashSet<>();
    Subject subject1 = new Subject("Math", new Department());
    Subject subject2 = new Subject("Physics", new Department());
    subjects.add(subject1);
    subjects.add(subject2);
    student.setSubjects(subjects);
    assertEquals(subjects, student.getSubjects());
  }

  @Test
  public void testGetDepartments() {
    Student student = new Student("Petr", "Petrov");
    List<Department> departments = new ArrayList<>();
    Department department1 = new Department("Computer Science", "description");
    Department department2 = new Department("Electrical Engineering","description" );
    departments.add(department1);
    departments.add(department2);
    student.setDepartments(departments);
    assertEquals(departments, student.getDepartments());
  }

  @Test
  public void testAddDepartment() {
    Student student = new Student("Petr", "Petrov");
    Department department = new Department("Computer Science","description");
    student.getDepartments().add(department);
    assertTrue(student.getDepartments().contains(department));
  }

  @Test
  public void testAddSubject() {
    Student student = new Student("Petr", "Petrov");
    Subject subject = new Subject("Math",new Department());
    student.getSubjects().add(subject);
    assertTrue(student.getSubjects().contains(subject));
  }
}
