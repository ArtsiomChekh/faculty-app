package com.chekh.artsiom.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.repository.DepartmentRepository;

import java.util.*;

import org.junit.Assert;
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
        List<Department> mockDepartments = new ArrayList<>();
        mockDepartments.add(new Department("Кафедра 1", "описание"));
        mockDepartments.add(new Department("Кафедра 2", "описание"));

        when(departmentRepository.findAll()).thenReturn(mockDepartments);

        List<Department> departments = departmentService.getAllDepartments();

        verify(departmentRepository, times(1)).findAll();
        assertEquals(mockDepartments, departments);
    }

    @Test
    public void testGetDepartmentById() {
        long testId = 1L;
        Department mockDepartment = new Department("Кафедра  1", "описание");
        when(departmentRepository.findById(testId)).thenReturn(Optional.of(mockDepartment));

        Department department = departmentService.getDepartmentById(testId);

        verify(departmentRepository, times(1)).findById(testId);
        assertEquals(mockDepartment, department);
    }

    @Test
    public void testSaveDepartment() {
        Department departmentToSave = new Department("Кафедра 1", "описание");

        departmentService.saveDepartment(departmentToSave);

        verify(departmentRepository, times(1)).save(departmentToSave);
    }

    @Test
    public void testDeleteDepartmentById() {
        long testId = 1L;

        departmentService.deleteDepartmentById(testId);

        verify(departmentRepository, times(1)).deleteById(testId);
    }

    @Test
    public void testGetDepartmentsStudentCount() {
        Department department1 = new Department("Кафедра 1", "описание 1");
        Department department2 = new Department("Кафедра 2", "описание 2");
        Department department3 = new Department("Кафедра 3", "описание 3");

        List<Object[]> departmentStudentCountList = new ArrayList<>();
        departmentStudentCountList.add(new Object[]{department1, 50L});
        departmentStudentCountList.add(new Object[]{department2, 75L});
        departmentStudentCountList.add(new Object[]{department3, 60L});

        when(departmentRepository.findDepartmentStudentCount()).thenReturn(departmentStudentCountList);

        Map<Department, Long> actual = departmentService.getDepartmentsStudentCount();
        Map<Department, Long> expected = new HashMap<>();
        expected.put(department1, 50L);
        expected.put(department2, 75L);
        expected.put(department3, 60L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetDepartmentsTeacherCount() {
        Department department1 = new Department("Кафедра 1", "описание 1");
        Department department2 = new Department("Кафедра 2", "описание 2");
        Department department3 = new Department("Кафедра 3", "описание 3");

        List<Object[]> departmentTeacherCountList = new ArrayList<>();
        departmentTeacherCountList.add(new Object[]{department1, 50L});
        departmentTeacherCountList.add(new Object[]{department2, 75L});
        departmentTeacherCountList.add(new Object[]{department3, 60L});

        when(departmentRepository.findDepartmentTeacherCount()).thenReturn(departmentTeacherCountList);

        Map<Department, Long> actual = departmentService.getDepartmentsTeacherCount();
        Map<Department, Long> expected = new HashMap<>();
        expected.put(department1, 50L);
        expected.put(department2, 75L);
        expected.put(department3, 60L);
        Assert.assertEquals(expected, actual);
    }




}