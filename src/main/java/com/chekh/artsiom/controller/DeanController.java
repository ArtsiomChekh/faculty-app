package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.service.DepartmentService;
import com.chekh.artsiom.service.StudentService;
import com.chekh.artsiom.service.SubjectService;
import com.chekh.artsiom.service.TeacherService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeanController {

  @Autowired
  private DepartmentService departmentService;

  @Autowired
  private TeacherService teacherService;

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private StudentService studentService;

  // получение списка всех кафедр

  @RequestMapping("/departments")
  public String showDepartments(@RequestParam(name = "sortBy", defaultValue = "none") String sortBy,
      @RequestParam(name = "isAscending", defaultValue = "true") boolean isAscending,
      Model model,
      HttpSession session) {
    List<Department> departments;

    String lastSortBy = (String) session.getAttribute("sortBy");
    if (lastSortBy != null && lastSortBy.equals(sortBy)) {
      isAscending = !isAscending;
    } else {
      isAscending = true;
      session.setAttribute("sortBy", sortBy);
    }

    if (sortBy.equals("students")) {
      departments = departmentService.getAllDepartmentsSortedByStudentCount(isAscending);
    } else if (sortBy.equals("teachers")) {
      departments = departmentService.getAllDepartmentsSortedByTeacherCount(isAscending);
    } else {
      departments = departmentService.getAllDepartments();
    }

    model.addAttribute("departments", departments);
    model.addAttribute("isAscending", isAscending);
    return "departments";
  }



  // получение списка всех преподавателей
  @GetMapping("/teachers")
  public String listTeachers(Model model) {
    List<Teacher> teachers = teacherService.findAll();
    List<Subject> subjects = subjectService.findAll();
    model.addAttribute("teachers", teachers);
    model.addAttribute("subjects", subjects);
    return "teachers";
  }

  // получение списка всех предметов
  @GetMapping("/subjects")
  public String getSubjects(Model model) {
    List<Subject> subjects = subjectService.getAllSubjects();
    model.addAttribute("subjects", subjects);
    return "dean/subjects";
  }

  // получение списка всех студентов
  @GetMapping("/students")
  public String getStudents(Model model) {
    List<Student> students = studentService.getAllStudents();
    model.addAttribute("students", students);
    return "dean/students";
  }

  // отображение формы для добавления новой кафедры
  @GetMapping("/departments/add")
  public String addDepartmentForm(Model model) {
    model.addAttribute("department", new Department());
    return "dean/addDepartment";
  }

  // добавление новой кафедры
  @PostMapping("/departments/add")
  public String addDepartment(@ModelAttribute("department") Department department) {
    departmentService.addDepartment(department);
    return "redirect:/dean/departments";
  }

  // отображение формы для редактирования кафедры
  @GetMapping("/departments/edit/{id}")
  public String editDepartmentForm(@PathVariable("id") Long id, Model model) {
    Department department = departmentService.getDepartmentById(id);
    model.addAttribute("department", department);
    return "dean/editDepartment";
  }

  // обновление кафедры
  @PostMapping("/departments/edit/{id}")
  public String editDepartment(@PathVariable("id") Long id, @ModelAttribute("department") Department department) {
    department.setId(id);
    departmentService.updateDepartment(department);
    return "redirect:/dean/departments";
  }

  // удаление кафедры
  @GetMapping("/departments/delete/{id}")
  public String deleteDepartment(@PathVariable("id") Long id) {
    departmentService.deleteDepartment(id);
    return "redirect:/dean/departments";
  }
}
