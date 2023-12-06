package com.chekh.artsiom.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TeacherTest {
    private Teacher teacher;
    private Department department;

    @Before
    public void setUp() {
        department = new Department("Department", "Description");
        teacher = new Teacher("Art", "Che", department);
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Art", teacher.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        teacher.setFirstName("Bill");
        assertEquals("Bill", teacher.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Che", teacher.getLastName());
    }

    @Test
    public void testSetLastName() {
        teacher.setLastName("Mack");
        assertEquals("Mack", teacher.getLastName());
    }

    @Test
    public void testGetDepartment() {
        assertEquals(department, teacher.getDepartment());
    }

    @Test
    public void testSetDepartment() {
        Department newDepartment = new Department("New Department", "Description");
        teacher.setDepartment(newDepartment);
        assertEquals(newDepartment, teacher.getDepartment());
    }

    @Test
    public void testGetId() {
        assertNull(teacher.getId());
    }

    @Test
    public void testSetId() {
        Long id = 1L;
        teacher.setId(id);
        assertEquals(id, teacher.getId());
    }
}
