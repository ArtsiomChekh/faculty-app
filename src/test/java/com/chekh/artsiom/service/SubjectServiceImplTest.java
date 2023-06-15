package com.chekh.artsiom.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    List<Subject> mockSubjects = Arrays.asList(
        new Subject("English", new Department("Кафедра 1", "описание")),
        new Subject("History", new Department("Кафедра 1", "описание"))
    );
    when(subjectRepository.findAll()).thenReturn(mockSubjects);

    List<Subject> subjects = subjectService.getAllSubjects();

    verify(subjectRepository, times(1)).findAll();
    assertEquals(mockSubjects, subjects);
  }

  @Test
  public void testGetSubjectsByDepartment() {

    Long departmentId = 1L;
    List<Subject> mockSubjects = Arrays.asList(
        new Subject("English", new Department("Кафедра 1", "описание")),
        new Subject("History", new Department("Кафедра 1", "описание"))
    );
    when(subjectRepository.findAllByDepartmentId(departmentId)).thenReturn(mockSubjects);

    List<Subject> subjects = subjectService.getSubjectsByDepartment(departmentId);

    verify(subjectRepository, times(1)).findAllByDepartmentId(departmentId);
    assertEquals(mockSubjects, subjects);
  }

  @Test
  public void testFindAll() {

    Subject subject1 = new Subject("English", new Department("Кафедра  2", "описание"));
    Subject subject2 = new Subject("History", new Department("Кафедра  3", "описание"));
    List<Subject> subjects = new ArrayList<>();
    subjects.add(subject1);
    subjects.add(subject2);

    when(subjectRepository.findAll()).thenReturn(subjects);

    List<Subject> result = subjectService.findAll();

    assertEquals(2, result.size());
    assertEquals(subject1, result.get(0));
    assertEquals(subject2, result.get(1));

    verify(subjectRepository, times(1)).findAll();
  }

  @Test
  public void testGetSubjectsByIds() {

    Long id1 = 1L;
    Long id2 = 2L;
    Subject subject1 = new Subject("English", new Department("Кафедра  2", "описание"));
    subject1.setId(id1);
    Subject subject2 = new Subject("History", new Department("Кафедра  3", "описание"));
    subject2.setId(id2);
    List<Subject> subjects = new ArrayList<>();
    subjects.add(subject1);
    subjects.add(subject2);

    when(subjectRepository.findByIdIn(Arrays.asList(id1, id2))).thenReturn(subjects);

    List<Subject> result = subjectService.getSubjectsByIds(Arrays.asList(id1, id2));

    assertEquals(2, result.size());
    assertEquals(subject1, result.get(0));
    assertEquals(subject2, result.get(1));

    verify(subjectRepository, times(1)).findByIdIn(Arrays.asList(id1, id2));
  }

  @Test
  public void testSaveSubject() {

    Subject subject = new Subject("English", new Department("Кафедра  2", "описание"));

    subjectService.saveSubject(subject);

    verify(subjectRepository, times(1)).save(subject);
  }

  @Test
  public void testDeleteSubjectById() {
    Long id = 1L;

    subjectService.deleteSubjectById(id);

    verify(subjectRepository, times(1)).deleteById(id);
  }

  @Test
  public void testGetSubjectById() {
    Long id = 1L;
    Subject subject = new Subject("English", new Department("Кафедра  2", "описание"));
    subject.setId(id);

    when(subjectRepository.findById(id)).thenReturn(Optional.of(subject));

    Subject result = subjectService.getSubjectById(id);

    assertEquals(subject, result);

    verify(subjectRepository, times(1)).findById(id);
  }

  @Test
  public void testGetSubjectByIdNotFound() {
    Long id = 1L;

    when(subjectRepository.findById(id)).thenReturn(Optional.empty());

    Subject result = subjectService.getSubjectById(id);

    assertEquals(null, result);

    verify(subjectRepository, times(1)).findById(id);
  }
}