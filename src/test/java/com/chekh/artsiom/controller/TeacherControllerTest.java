package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.service.DepartmentService;
import com.chekh.artsiom.service.SubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @MockBean
    private DepartmentService departmentService;

    @Test
    public void testShowForTeacherPage() throws Exception {
        List<Subject> subjects = Arrays.asList(
                new Subject("Предмет 1"),
                new Subject("Предмет 2"));
        List<Department> departments = Arrays.asList(
                new Department("Кафедра 1"),
                new Department("Кафедра 2"));

        Mockito.when(subjectService.getAllSubjects()).thenReturn(subjects);
        Mockito.when(departmentService.getAllDepartments()).thenReturn(departments);

        mockMvc.perform(MockMvcRequestBuilders.get("/for-teacher"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("for-teacher"))
                .andExpect(MockMvcResultMatchers.model().attribute("subjects", subjects))
                .andExpect(MockMvcResultMatchers.model().attribute("departments", departments));
    }
}
