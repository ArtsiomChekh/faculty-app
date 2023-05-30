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


  @RequestMapping("/departments")
  public String getDepartmentsPage(
      @RequestParam(name = "sortBy", defaultValue = "none") String sortBy,
      @RequestParam(name = "isAscending", defaultValue = "true") boolean isAscending, // the sorting order (true - ascending, false - descending)
      Model model,
      HttpSession session // the session object used to store the last sorting criteria
  ) {
    List<Department> departments;

    // check if the user has changed the sorting criteria
    String lastSortBy = (String) session.getAttribute("sortBy");
    if (lastSortBy != null && lastSortBy.equals(sortBy)) {
      isAscending = !isAscending;
    } else {
      isAscending = true;
      session.setAttribute("sortBy", sortBy); // store the current criteria in the session
    }

    // get the list of departments sorted based on the selected criteria and sorting order
    if (sortBy.equals("students")) {
      departments = departmentService.getAllDepartmentsSortedByStudentCount(isAscending);
    } else if (sortBy.equals("teachers")) {
      departments = departmentService.getAllDepartmentsSortedByTeacherCount(isAscending);
    } else {
      departments = departmentService.getAllDepartments(); // no sorting criteria specified
    }

    model.addAttribute("departments", departments);
    model.addAttribute("isAscending", isAscending);

    return "departments";
  }

  @GetMapping("/teachers")
  public String getTeachersPage(
      @RequestParam(value = "department", required = false) Long departmentId, // the ID of the department to filter by (optional)
      @RequestParam(value = "subject", required = false) Long subjectId,
      Model model
  ) {
    List<Teacher> teachers;

    // filter teachers by department and subject
    if (departmentId != null && subjectId != null) {
      teachers = teacherService.findByDepartmentIdAndSubjectsId(departmentId, subjectId);
    }
    // filter teachers by department
    else if (departmentId != null) {
      teachers = teacherService.findByDepartmentId(departmentId);
    }
    // filter teachers by subject
    else if (subjectId != null) {
      teachers = teacherService.findBySubjectId(subjectId);
    }
    // get all teachers
    else {
      teachers = teacherService.getAllTeachers();
    }

    // get all departments and subjects for filtering
    List<Department> departments = departmentService.getAllDepartments();
    List<Subject> subjects = subjectService.getAllSubjects();

    // add the filtered list of teachers and the departments and subjects to the model object
    model.addAttribute("teachers", teachers);
    model.addAttribute("departments", departments);
    model.addAttribute("subjects", subjects);

    return "teachers";
  }

  @GetMapping("/subjects")
  public String getSubjectsPage(
      @RequestParam(name = "department", required = false) Long departmentId,
      Model model
  ) {
    List<Department> departments = departmentService.getAllDepartments();
    List<Subject> subjects;

    // filter subjects by department
    if (departmentId != null) {
      Department department = departmentService.getDepartmentById(departmentId);
      if (department == null) {
        // handle error: department not found
        return "error";
      }
      subjects = subjectService.getSubjectsByDepartment(departmentId);
    }
    // get all subjects
    else {
      subjects = subjectService.getAllSubjects();
    }

    // add the list of departments, subjects, and selected department ID to the model object
    model.addAttribute("departments", departments);
    model.addAttribute("subjects", subjects);
    model.addAttribute("selectedDepartmentId", departmentId);

    // return the view name to be rendered by the view resolver
    return "subjects";
  }


  @GetMapping("/students")
  public String getStudents(
      @RequestParam(value = "department", required = false) Long departmentId, // the ID of the department to filter by (optional)
      @RequestParam(value = "subject", required = false) Long subjectId, // the ID of the subject to filter by (optional)
      Model model // the model object used to pass data to the view
  ) {
    List<Student> students;

    // filter students by department and subject
    if (departmentId != null && subjectId != null) {
      students = studentService.findByDepartmentIdAndSubjectsId(departmentId, subjectId);
    }
    // filter students by department
    else if (departmentId != null) {
      students = studentService.findByDepartmentId(departmentId);
    }
    // filter students by subject
    else if (subjectId != null) {
      students = studentService.findBySubjectId(subjectId);
    }
    // get all students
    else {
      students = studentService.getAllStudents();
    }

    // get all departments and subjects for filtering
    List<Department> departments = departmentService.getAllDepartments();
    List<Subject> subjects = subjectService.getAllSubjects();

    // add the filtered list of students and the departments and subjects to the model object
    model.addAttribute("students", students);
    model.addAttribute("departments", departments);
    model.addAttribute("subjects", subjects);

    return "students";
  }

  @GetMapping("/showNewDepartmentForm")
  public String showNewDepartmentForm(Model model) {

    Department department = new Department();

    // add the Department object to the model object
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

    // get all departments and subjects for selecting in the form
    List<Department> departments = departmentService.getAllDepartments();
    List<Subject> subjects = subjectService.getAllSubjects();

    // add the Teacher object and the departments and subjects to the model object
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
