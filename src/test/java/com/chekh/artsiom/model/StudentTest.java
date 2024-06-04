package com.chekh.artsiom.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StudentTest {
    private Student student;

    @Before
    public void setUp() {
        student = new Student("Art", "Che");
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Art", student.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        student.setFirstName("Bill");
        assertEquals("Bill", student.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Che", student.getLastName());
    }

    @Test
    public void testSetLastName() {
        student.setLastName("Mack");
        assertEquals("Mack", student.getLastName());
    }

    @Test
    public void testGetId() {
        assertNull(student.getId());
    }

    @Test
    public void testSetId() {
        Long id = 1L;
        student.setId(id);
        assertEquals(id, student.getId());
    }
}
