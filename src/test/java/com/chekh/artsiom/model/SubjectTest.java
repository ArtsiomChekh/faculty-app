package com.chekh.artsiom.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SubjectTest {

  private Subject subject;
  private Department department;

  @Before
  public void setUp() {
    department = new Department("Department", "Description");
    subject = new Subject("English", department);
  }

  @Test
  public void testGetName() {
    assertEquals("English", subject.getName());
  }

  @Test
  public void testSetName() {
    subject.setName("New Subject");
    assertEquals("New Subject", subject.getName());
  }

  @Test
  public void testGetDepartment() {
    assertEquals(department, subject.getDepartment());
  }

  @Test
  public void testSetDepartment() {
    Department newDepartment = new Department("New Department", "Description");
    subject.setDepartment(newDepartment);
    assertEquals(newDepartment, subject.getDepartment());
  }

  @Test
  public void testGetId() {
    assertNull(subject.getId());
  }

  @Test
  public void testSetId() {
    Long id = 1L;
    subject.setId(id);
    assertEquals(id, subject.getId());
  }

}
