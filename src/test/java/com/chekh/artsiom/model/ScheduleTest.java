package com.chekh.artsiom.model;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ScheduleTest {
    private Schedule schedule;

    @Before
    public void setUp() {
        schedule = new Schedule(
                new Subject("Предмет 1", new Department("Кафедра 1")),
                new Teacher("Николя", "Григорик"),
                1, LocalDateTime.of(2023, 12, 12, 8, 0),
                LocalDateTime.of(2023, 12, 12, 10, 0));
    }

    @Test
    public void testGetSubjectName() {
        assertEquals("Предмет 1", schedule.getSubject().getName());
    }

    @Test
    public void testGetDepartmentName() {
        assertEquals("Кафедра 1", schedule.getSubject().getDepartment().getName());
    }

    @Test
    public void testGetTeacherName() {
        assertEquals("Николя", schedule.getTeacher().getFirstName());
        assertEquals("Григорик", schedule.getTeacher().getLastName());
    }

    @Test
    public void testGetDayOfWeekName() {
        assertEquals(DayOfWeek.MONDAY, schedule.getWeekDay());
    }

    @Test
    public void testGetStartTime() {
        LocalDateTime expected = LocalDateTime.of(2023, 12, 12, 8, 0);
        assertEquals(expected, schedule.getStartTime());
    }

    @Test
    public void testGetEndTime() {
        LocalDateTime expected = LocalDateTime.of(2023, 12, 12, 10, 0);
        assertEquals(expected, schedule.getEndTime());
    }

    @Test
    public void testGetId() {
        assertNull(schedule.getId());
    }

    @Test
    public void testSetId() {
        Long id = 1L;
        schedule.setId(id);
        assertEquals(id, schedule.getId());
    }
}
