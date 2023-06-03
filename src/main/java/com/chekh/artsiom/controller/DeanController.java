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

  @GetMapping("/departments")
  public String getAllDepartments(Model model) {
    List<Department> departments = departmentService.getAllDepartments();
    model.addAttribute("departments", departments);
    return "departments";
  }

  @GetMapping("/teachers")
  public String getTeachersPage(
      @RequestParam(value = "department", required = false) Long departmentId,
      // the ID of the department to filter by (optional)
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
      @RequestParam(value = "department", required = false) Long departmentId,
      // the ID of the department to filter by (optional)
      @RequestParam(value = "subject", required = false) Long subjectId,
      // the ID of the subject to filter by (optional)
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
    model.addAttribute("teacher", new Teacher());
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("title", "Add Teacher");
    return "teacher_form";
  }

  @GetMapping("/editTeacherForm/{teacherId}")
  public String showEditTeacherForm(@PathVariable(value = "teacherId") Long teacherId, Model model){
    model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("title", "Edit Teacher");
    return "teacher_form";
  }

  @PostMapping("saveTeacher")
  public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
    if(teacher.getId() == null){
      teacherService.saveTeacher(teacher);
    } else {
      Teacher existingTeacher = teacherService.getTeacherById(teacher.getId());
      existingTeacher.setFirstName(teacher.getFirstName());
      existingTeacher.setLastName(teacher.getLastName());
      existingTeacher.setDepartment(teacher.getDepartment());
      existingTeacher.setSubjects(teacher.getSubjects());
      teacherService.saveTeacher(existingTeacher);
    }
    return "redirect:/teachers";
  }

  @GetMapping("/deleteTeacher/{id}")
  public String deleteTeacher(@PathVariable(value = "id") long id) {
    teacherService.deleteTeacherById(id);
    return "redirect:/teachers";
  }


  @GetMapping("/newSubjectForm")
  public String showNewSubjectForm(Model model) {
    model.addAttribute("subject", new Subject());
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("title", "Add Subject");
    return "subject_form";
  }

  @GetMapping("/editSubjectForm/{subjectId}")
  public String showEditSubjectForm(@PathVariable("subjectId") Long subjectId, Model model) {
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subject", subjectService.getSubjectById(subjectId));
    model.addAttribute("title", "Edit Subject");
    return "subject_form";
  }

  @PostMapping("/saveSubject")
  public String saveSubject(@ModelAttribute("subject") Subject subject) {
    if (subject.getId() == null) {
      subjectService.saveSubject(subject);
    } else {
      Subject existingSubject = subjectService.getSubjectById(subject.getId());
      existingSubject.setName(subject.getName());
      existingSubject.setDepartment(subject.getDepartment());
      subjectService.saveSubject(existingSubject);
    }
    return "redirect:/subjects";
  }

  @GetMapping("/deleteSubject/{id}")
  public String deleteSubject(@PathVariable(value = "id") long id) {
    subjectService.deleteSubjectById(id);
    return "redirect:/subjects";
  }

  @GetMapping("/newStudentForm")
  public String showNewStudentForm(Model model) {
    model.addAttribute("student", new Student());
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("title", "Add Student");
    return "student_form";
  }

  @GetMapping("/editStudentForm/{studentId}")
  public String showEditStudentForm(@PathVariable("studentId") Long studentId, Model model) {
    model.addAttribute("student", studentService.getStudentById(studentId));
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("title", "Edit Student");
    return "student_form";
  }

  @PostMapping("/saveStudent")
  public String saveStudent(@ModelAttribute("student") Student student) {
    if (student.getId() == null) {
      studentService.saveStudent(student);
    } else {
      Student existingStudent = studentService.getStudentById(student.getId());
      existingStudent.setFirstName(student.getFirstName());
      existingStudent.setLastName(student.getLastName());
      existingStudent.setDepartments(student.getDepartments());
      existingStudent.setSubjects(student.getSubjects());
      studentService.saveStudent(existingStudent);
    }
    return "redirect:/students";
  }

  @GetMapping("/deleteStudent/{id}")
  public String deleteStudent(@PathVariable("id") long id) {
    studentService.deleteStudentById(id);
    return "redirect:/students";
  }

}
