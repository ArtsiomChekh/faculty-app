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
    // Setup
    List<Student> mockStudents = Arrays.asList(
        new Student("Art", "Che"),
        new Student("Art", "Che")
    );
    when(studentRepository.findAll()).thenReturn(mockStudents);

    // Execution
    List<Student> students = studentService.getAllStudents();

    // Verification
    verify(studentRepository, times(1)).findAll();
    assertEquals(mockStudents, students);
  }

  @Test
  public void testFindByDepartmentId() {
    // Setup
    Long departmentId = 1L;
    List<Student> mockStudents = Arrays.asList(
        new Student("Art", "Che"),
        new Student("Art", "Che")
    );
    when(studentRepository.findAllByDepartments_Id(departmentId)).thenReturn(mockStudents);

    // Execution
    List<Student> students = studentService.findByDepartmentId(departmentId);

    // Verification
    verify(studentRepository, times(1)).findAllByDepartments_Id(departmentId);
    assertEquals(mockStudents, students);
  }

  @Test
  public void testFindBySubjectId() {
    // Setup
    Long subjectId = 1L;
    List<Student> mockStudents = Arrays.asList(
        new Student("Art", "Che"),
        new Student("Art", "Che")
    );
    when(studentRepository.findAllBySubjects_Id(subjectId)).thenReturn(mockStudents);

    // Execution
    List<Student> students = studentService.findBySubjectId(subjectId);

    // Verification
    verify(studentRepository, times(1)).findAllBySubjects_Id(subjectId);
    assertEquals(mockStudents, students);
  }

  @Test
  public void testFindByDepartmentIdAndSubjectsId() {
    // Setup
    Long departmentId = 1L;
    Long subjectId = 2L;
    List<Student> mockStudents = Arrays.asList(
        new Student("Art", "Che"),
        new Student("Art", "Che")
    );
    when(studentRepository.findByDepartments_IdAndSubjects_Id(departmentId, subjectId)).thenReturn(mockStudents);

    // Execution
    List<Student> students = studentService.findByDepartmentIdAndSubjectsId(departmentId, subjectId);

    // Verification
    verify(studentRepository, times(1)).findByDepartments_IdAndSubjects_Id(departmentId, subjectId);
    assertEquals(mockStudents, students);
  }
}