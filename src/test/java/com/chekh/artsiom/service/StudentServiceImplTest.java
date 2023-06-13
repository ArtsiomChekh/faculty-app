package com.chekh.artsiom.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.repository.StudentRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StudentServiceImplTest {

  @Mock
  private StudentRepository studentRepository;

  @InjectMocks
  private StudentServiceImpl studentService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllStudents() {
    List<Student> mockStudents = Arrays.asList(
        new Student("Art", "Che"),
        new Student("Art", "Che")
    );

    when(studentRepository.findAll()).thenReturn(mockStudents);

    List<Student> students = studentService.getAllStudents();

    verify(studentRepository, times(1)).findAll();
    assertEquals(mockStudents, students);
  }

  @Test
  public void testFindByDepartmentId() {

    Long departmentId = 1L;
    List<Student> mockStudents = Arrays.asList(
        new Student("Art", "Che"),
        new Student("Art", "Che")
    );
    when(studentRepository.findByDepartmentId(departmentId)).thenReturn(mockStudents);

    List<Student> students = studentService.findByDepartmentId(departmentId);

    verify(studentRepository, times(1)).findByDepartmentId(departmentId);

    assertEquals(mockStudents, students);
  }

  @Test
  public void testFindBySubjectId() {

    Long subjectId = 1L;
    List<Student> mockStudents = Arrays.asList(
        new Student("Art", "Che"),
        new Student("Art", "Che")
    );
    when(studentRepository.findBySubjectId(subjectId)).thenReturn(mockStudents);

    List<Student> students = studentService.findBySubjectId(subjectId);

    verify(studentRepository, times(1)).findBySubjectId(subjectId);
    assertEquals(mockStudents, students);
  }

  @Test
  public void testFindByDepartmentIdAndSubjectsId() {

    Long departmentId = 1L;
    Long subjectId = 2L;
    List<Student> mockStudents = Arrays.asList(
        new Student("Art", "Che"),
        new Student("Art", "Che")
    );
    when(studentRepository.findByDepartmentIdAndSubjectId(departmentId, subjectId)).thenReturn(
        mockStudents);

    List<Student> students = studentService.findByDepartmentIdAndSubjectId(departmentId,
        subjectId);

    verify(studentRepository, times(1)).findByDepartmentIdAndSubjectId(departmentId, subjectId);
    assertEquals(mockStudents, students);
  }
}