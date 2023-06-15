package com.chekh.artsiom.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {

  private Department department;

  @Before
  public void setUp() {
    department = new Department("Кафедра 1", "Описание");
  }

  @Test
  public void testGetName() {
    assertEquals("Кафедра 1", department.getName());
  }

  @Test
  public void testSetName() {
    department.setName("New Department");
    assertEquals("New Department", department.getName());
  }

  @Test
  public void testGetDescription() {
    assertEquals("Описание", department.getDescription());
  }

  @Test
  public void testSetDescription() {
    department.setDescription("New description");
    assertEquals("New description", department.getDescription());
  }

  @Test
  public void testGetId() {
    assertNull(department.getId());
  }

  @Test
  public void testSetId() {
    Long id = 1L;
    department.setId(id);
    assertEquals(id, department.getId());
  }
}