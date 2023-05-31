package com.chekh.artsiom.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
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

  @Test
  public void testGetSubjectsByTeacher() {
    // Setup test data
    Long teacherId = 1L;

    Teacher teacher = new Teacher("Иван", "Иванов", new Department("Кафедра  1", 10, 5, "описание"));
    teacher.setId(teacherId);
    Subject subject1 = new Subject("English", new Department("Кафедра  2", 10, 5, "описание"));
    subject1.addTeacher(teacher);
    Subject subject2 = new Subject("History", new Department("Кафедра  3", 10, 5, "описание"));
    subject2.addTeacher(teacher);
    List<Subject> subjects = new ArrayList<>();
    subjects.add(subject1);
    subjects.add(subject2);

    // Mock the repository method
    when(subjectRepository.findAllByTeachersId(teacherId)).thenReturn(subjects);

    // Execution
    List<Subject> result = subjectService.getSubjectsByTeacher(teacherId);

    // Verification
    assertEquals(2, result.size());
    assertEquals(subject1, result.get(0));
    assertEquals(subject2, result.get(1));

    // Verify the repository method was called
    verify(subjectRepository, times(1)).findAllByTeachersId(teacherId);
  }

  @Test
  public void testFindAll() {
    // Setup test data
    Subject subject1 = new Subject("English", new Department("Кафедра  2", 10, 5, "описание"));
    Subject subject2 = new Subject("History", new Department("Кафедра  3", 10, 5, "описание"));
    List<Subject> subjects = new ArrayList<>();
    subjects.add(subject1);
    subjects.add(subject2);

    // Mock the repository method
    when(subjectRepository.findAll()).thenReturn(subjects);

    // Execution
    List<Subject> result = subjectService.findAll();

    // Verification
    assertEquals(2, result.size());
    assertEquals(subject1, result.get(0));
    assertEquals(subject2, result.get(1));

    // Verify the repository method was called
    verify(subjectRepository, times(1)).findAll();
  }

  @Test
  public void testGetSubjectsByIds() {
    // Setup test data
    Long id1 = 1L;
    Long id2 = 2L;
    Subject subject1 = new Subject("English", new Department("Кафедра  2", 10, 5, "описание"));
    subject1.setId(id1);
    Subject subject2 = new Subject("History", new Department("Кафедра  3", 10, 5, "описание"));
    subject2.setId(id2);
    List<Subject> subjects = new ArrayList<>();
    subjects.add(subject1);
    subjects.add(subject2);

    // Mock the repository method
    when(subjectRepository.findByIdIn(Arrays.asList(id1, id2))).thenReturn(subjects);

    // Execution
    List<Subject> result = subjectService.getSubjectsByIds(Arrays.asList(id1, id2));

    // Verification
    assertEquals(2, result.size());
    assertEquals(subject1, result.get(0));
    assertEquals(subject2, result.get(1));

    // Verify the repository method was called
    verify(subjectRepository, times(1)).findByIdIn(Arrays.asList(id1, id2));
  }

  @Test
  public void testSaveSubject() {
    // Setup test data
    Subject subject = new Subject("English", new Department("Кафедра  2", 10, 5, "описание"));

    // Execution
    subjectService.saveSubject(subject);

    // Verification
    verify(subjectRepository, times(1)).save(subject);
  }

  @Test
  public void testDeleteSubjectById() {
    // Setup test data
    Long id = 1L;

    // Execution
    subjectService.deleteSubjectById(id);

    // Verification
    verify(subjectRepository, times(1)).deleteById(id);
  }

  @Test
  public void testGetSubjectById() {
    // Setup test data
    Long id = 1L;
    Subject subject = new Subject("English", new Department("Кафедра  2", 10, 5, "описание"));
    subject.setId(id);

    // Mock the repository method
    when(subjectRepository.findById(id)).thenReturn(Optional.of(subject));

    // Execution
    Subject result = subjectService.getSubjectById(id);

    // Verification
    assertEquals(subject, result);

    // Verify the repository method was called
    verify(subjectRepository, times(1)).findById(id);
  }

  @Test
  public void testGetSubjectByIdNotFound() {
    // Setup test data
    Long id = 1L;

    // Mock the repository method
    when(subjectRepository.findById(id)).thenReturn(Optional.empty());

    // Execution
    Subject result = subjectService.getSubjectById(id);

    // Verification
    assertEquals(null, result);

    // Verify the repository method was called
    verify(subjectRepository, times(1)).findById(id);
  }
}