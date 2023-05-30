package com.chekh.artsiom.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.repository.DepartmentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DepartmentServiceImplTest {

  @Mock
  private DepartmentRepository departmentRepository;

  @InjectMocks
  private DepartmentServiceImpl departmentService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllDepartments() {
    // Setup
    List<Department> mockDepartments = new ArrayList<>();
    mockDepartments.add(new Department("Department 1", 10, 5, "описание"));
    mockDepartments.add(new Department("Department 2", 20, 15, "описание"));

    when(departmentRepository.findAll()).thenReturn(mockDepartments);

    // Execution
    List<Department> departments = departmentService.getAllDepartments();

    // Verification
    verify(departmentRepository, times(1)).findAll();
    assertEquals(mockDepartments, departments);
  }


}