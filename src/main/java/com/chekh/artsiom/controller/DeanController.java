package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.service.DepartmentService;
import com.chekh.artsiom.service.StudentService;
import com.chekh.artsiom.service.SubjectService;
import com.chekh.artsiom.service.TeacherService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
  public String getDepartmentsPage(@RequestParam(name = "sortBy", defaultValue = "none") String sortBy,
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
  public String getTeachersPage(@RequestParam(value = "department", required = false) Long departmentId,
      @RequestParam(value = "subject", required = false) Long subjectId,
      Model model) {
    List<Teacher> teachers;
    if (departmentId != null && subjectId != null) {
      // фильтрация по кафедре и предмету
      teachers = teacherService.findByDepartmentIdAndSubjectsId(departmentId, subjectId);
    } else if (departmentId != null) {
      // фильтрация по кадедре
      teachers = teacherService.findByDepartmentId(departmentId);
    } else if (subjectId != null) {
      // фильтрация по предмету
      teachers = teacherService.findBySubjectId(subjectId);
    } else {
      // получение всех преподавателей
      teachers = teacherService.getAllTeachers();
    }
    List<Department> departments = departmentService.getAllDepartments();
    List<Subject> subjects = subjectService.getAllSubjects();
    model.addAttribute("teachers", teachers);
    model.addAttribute("departments", departments);
    model.addAttribute("subjects", subjects);
    return "teachers";
  }

  // получение списка всех предметов
  @GetMapping("/subjects")
  public String getSubjectsPage(@RequestParam(name = "departmentId", required = false) Long departmentId, Model model) {
    List<Department> departments = departmentService.getAllDepartments();
    List<Subject> subjects;
    if (departmentId != null) {
      Department department = departmentService.getDepartmentById(departmentId);
      if (department == null) {
        // обработка ошибки: кафедра не найдена
        return "error";
      }
      subjects = subjectService.getSubjectsByDepartment(departmentId);
    } else {
      subjects = subjectService.getAllSubjects();
    }
    model.addAttribute("departments", departments);
    model.addAttribute("subjects", subjects);
    model.addAttribute("selectedDepartmentId", departmentId);
    return "subjects";
  }

  // получение списка всех студентов
  @GetMapping("/students")
  public String getStudents(@RequestParam(value = "department", required = false) Long departmentId,
      @RequestParam(value = "subject", required = false) Long subjectId,
      Model model) {
    List<Student> students;
    if (departmentId != null && subjectId != null) {
      // фильтрация по кафедре и предмету
      students = studentService.findByDepartmentIdAndSubjectsId(departmentId, subjectId);
    } else if (departmentId != null) {
      // фильтрация по кадедре
      students = studentService.findByDepartmentId(departmentId);
    } else if (subjectId != null) {
      // фильтрация по предмету
      students = studentService.findBySubjectId(subjectId);
    } else {
      // получение всех студентов
      students = studentService.getAllStudents();
    }
    List<Department> departments = departmentService.getAllDepartments();
    List<Subject> subjects = subjectService.getAllSubjects();
    model.addAttribute("departments", departments);
    model.addAttribute("subjects", subjects);
    model.addAttribute("students", students);
    return "students";
  }

  // отображение формы для добавления новой кафедры

  @GetMapping("/showNewDepartmentForm")
  public String showNewDepartmentForm(Model model) {

    Department department = new Department();

    model.addAttribute("department", department);

    return "new_department";
  }

  @PostMapping("/saveDepartment/{id}")
  public String saveDepartment(@PathVariable(value = "id") long id,
      @Valid @ModelAttribute("department") Department department,
      BindingResult bindingResult,
      Model model) {

    // Set the id of the department to the id from the path variable
    department.setId(id);

    if (bindingResult.hasErrors()) {
      // Add error messages to the model and return to the form
      model.addAttribute("errors", bindingResult.getAllErrors());
      if (id == 0) {
        // Return to the new_department form with errors
        return "new_department";
      } else {
        // Return to the update_department form with errors
        return "update_department";
      }
    } else {
      // Save the department and redirect to the department list page
      departmentService.saveDepartment(department);
      return "redirect:/departments";
    }
  }


  @GetMapping("/deleteDepartment/{id}")
  public String deleteDepartment(@PathVariable(value = "id") long id) {

    // call delete book method
    this.departmentService.deleteDepartmentById(id);

    return "redirect:/departments";
  }

  @GetMapping("/showUpdateDepartmentForm/{id}")
  public String showUpdateDepartmentForm(@PathVariable(value = "id") long id, Model model) {


    Department department = departmentService.getDepartmentById(id);

    // set department as a model attribute to pre-populate the form
    model.addAttribute("department", department);

    return "update_department";
  }


  @GetMapping("/showNewTeacherForm")
  public String showNewTeacherForm(Model model) {

    Teacher teacher = new Teacher();

    List<Department> departments = departmentService.getAllDepartments();
    List<Subject> subjects = subjectService.getAllSubjects();

    model.addAttribute("teacher", teacher);
    model.addAttribute("departments", departments);
    model.addAttribute("subjects", subjects);

    return "new_teacher";
  }

  @PostMapping("/saveTeacher/{id}")
  public String saveTeacher(@PathVariable(value = "id") long id,
      @Valid @ModelAttribute("teacher") Teacher teacher,
      BindingResult bindingResult,
      @RequestParam(value = "subjectIds", required = false) Long[] subjectIds,
      Model model) {

    // Set the id of the teacher to the id from the path variable
    teacher.setId(id);

    // Set the selected subject IDs to the teacher object
    if (subjectIds != null) {
      List<Subject> selectedSubjects = subjectService.getSubjectsByIds(Arrays.asList(subjectIds));
      teacher.setSubjects(selectedSubjects);

      if (bindingResult.hasErrors()) {
        // Add error messages to the model and return to the form
        model.addAttribute("errors", bindingResult.getAllErrors());
        if (id == 0) {
          // Return to the new_teacher form with errors
          return "new_teacher";
        } else {
          // Return to the update_teacher form with errors
          return "update_teacher";
        }
      } else {
        // Save the teacher and redirect to the teacher list page
        teacherService.saveTeacher(teacher);

      }
    }
    return "redirect:/teachers";
  }


  @GetMapping("/deleteTeacher/{id}")
  public String deleteTeacher(@PathVariable(value = "id") long id) {

    // call delete book method
    teacherService.deleteTeacherById(id);

    return "redirect:/teachers";
  }

  @GetMapping("/showUpdateTeacherForm/{id}")
  public String showUpdateTeacherForm(@PathVariable(value = "id") long id, Model model) {

    Teacher teacher = teacherService.getTeacherById(id);

    List<Subject> subjects = subjectService.getAllSubjects();
    List<Department> departments = departmentService.getAllDepartments();

    model.addAttribute("teacher", teacher);
    model.addAttribute("subjects", subjects);
    model.addAttribute("departments", departments);

    return "update_teacher";
  }

}
