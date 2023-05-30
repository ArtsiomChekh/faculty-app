package com.chekh.artsiom.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.repository.SubjectRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SubjectServiceImplTest {

  @Mock
  private SubjectRepository subjectRepository;

  @InjectMocks
  private SubjectServiceImpl subjectService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllSubjects() {
    // Setup
    List<Subject> mockSubjects = Arrays.asList(
        new Subject("English", new Department("Кафедра 1", 1, 2, "описание")),
        new Subject("History", new Department("Кафедра 1", 1, 2, "описание"))
    );
    when(subjectRepository.findAll()).thenReturn(mockSubjects);

    // Execution
    List<Subject> subjects = subjectService.getAllSubjects();

    // Verification
    verify(subjectRepository, times(1)).findAll();
    assertEquals(mockSubjects, subjects);
  }

  @Test
  public void testGetSubjectsByDepartment() {
    // Setup
    Long departmentId = 1L;
    List<Subject> mockSubjects = Arrays.asList(
        new Subject("English", new Department("Кафедра 1", 1, 2, "описание")),
        new Subject("History", new Department("Кафедра 1", 1, 2, "описание"))
    );
    when(subjectRepository.findAllByDepartmentId(departmentId)).thenReturn(mockSubjects);

    // Execution
    List<Subject> subjects = subjectService.getSubjectsByDepartment(departmentId);

    // Verification
    verify(subjectRepository, times(1)).findAllByDepartmentId(departmentId);
    assertEquals(mockSubjects, subjects);
  }
}