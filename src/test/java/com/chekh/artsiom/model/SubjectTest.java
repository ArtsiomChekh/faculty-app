package com.chekh.artsiom.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SubjectTest {

  private Subject subject;
  private Department department;

  @Before
  public void setUp() {
    subject = new Subject("Math");
    department = new Department("Computer Science", "description");
  }

  @Test
  public void testSetName() {
    subject.setName("Physics");
    assertEquals("Physics", subject.getName());
  }

  @Test
  public void testSetDepartment() {
    System.out.println("Before setting department: subject = " + subject + ", department = " + department);
    subject.setDepartment(department);
    System.out.println("Subject department: " + subject.getDepartment());
    System.out.println("Department subjects: " + department.getSubjects());
    assertNotNull(subject.getDepartment());
    assertEquals(department, subject.getDepartment());
    assertEquals(1, department.getSubjects().size());
  }


}
