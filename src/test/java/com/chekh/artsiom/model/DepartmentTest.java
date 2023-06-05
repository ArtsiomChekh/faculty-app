package com.chekh.artsiom.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {
  private Department department;
  private Set<Subject> subjects;

  @Before
  public void setUp() {
    department = new Department();
    subjects = new HashSet<>();
    subjects.add(new Subject("Math", new Department()));
    subjects.add(new Subject("Physics", new Department()));
  }

  @Test
  public void testGetSubjects() {
    department.setSubjects(subjects);
    assertEquals(subjects, department.getSubjects());
  }

  @Test
  public void testSetSubjects() {
    department.setSubjects(subjects);
    assertNotNull(department.getSubjects());
    assertEquals(2, department.getSubjects().size());
  }
}