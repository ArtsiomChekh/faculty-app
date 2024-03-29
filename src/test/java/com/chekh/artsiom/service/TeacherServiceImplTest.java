package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.TeacherRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TeacherServiceImplTest {

  @Mock
  private TeacherRepository teacherRepository;

  @InjectMocks
  private TeacherServiceImpl teacherService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllTeachers() {
    Teacher teacher1 = new Teacher("Ивана", "Миронов",
        new Department("Кафедра 1", "описание"));
    Teacher teacher2 = new Teacher("Николай", "Мирный",
        new Department("Кафедра 2", "описание"));
    List<Teacher> teachers = new ArrayList<>();
    teachers.add(teacher1);
    teachers.add(teacher2);

    when(teacherRepository.findAll()).thenReturn(teachers);

    List<Teacher> result = teacherService.getAllTeachers();

    assertEquals(2, result.size());
    assertEquals(teacher1, result.get(0));
    assertEquals(teacher2, result.get(1));

    verify(teacherRepository, times(1)).findAll();
  }

  @Test
  public void testFindByDepartmentId() {
    Long departmentId = 1L;
    Department department1 = new Department("Кафедра 1", "описание");
    Department department2 = new Department("Кафедра 1", "описание");
    Teacher teacher1 = new Teacher("Иван", "Миронов", department1);
    Teacher teacher2 = new Teacher("Николай", "Мирный", department2);
    List<Teacher> teachers = new ArrayList<>();
    teachers.add(teacher1);
    teachers.add(teacher2);

    when(teacherRepository.findByDepartmentId(departmentId)).thenReturn(teachers);

    List<Teacher> result = teacherService.findByDepartmentId(departmentId);

    assertEquals(2, result.size());
    assertEquals(teacher1, result.get(0));
    assertEquals(teacher2, result.get(1));

    verify(teacherRepository, times(1)).findByDepartmentId(departmentId);
  }


}