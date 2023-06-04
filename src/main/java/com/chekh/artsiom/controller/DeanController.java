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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    model.addAttribute("departments", departmentService.getAllDepartments());
    return "departments";
  }

  @GetMapping("/teachers")
  public String getTeachersPage(
      @RequestParam(value = "department", required = false) Long departmentId,
      @RequestParam(value = "subject", required = false) Long subjectId,
      Model model) {
    List<Teacher> teachers;

    if (departmentId != null && subjectId != null) {
      teachers = teacherService.findByDepartmentIdAndSubjectsId(departmentId, subjectId);
    } else if (departmentId != null) {
      teachers = teacherService.findByDepartmentId(departmentId);
    } else if (subjectId != null) {
      teachers = teacherService.findBySubjectId(subjectId);
    } else {
      teachers = teacherService.getAllTeachers();
    }

    model.addAttribute("teachers", teachers);
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjectService.getAllSubjects());

    return "teachers";
  }

  @GetMapping("/subjects")
  public String getSubjectsPage(
      @RequestParam(name = "department", required = false) Long departmentId,
      Model model) {
    List<Subject> subjects;

    if (departmentId != null) {
      Department department = departmentService.getDepartmentById(departmentId);
      if (department == null) {
        return "error";
      }
      subjects = subjectService.getSubjectsByDepartment(departmentId);
    } else {
      subjects = subjectService.getAllSubjects();
    }

    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjects);
    model.addAttribute("selectedDepartmentId", departmentId);

    return "subjects";
  }

  @GetMapping("/students")
  public String getStudents(
      @RequestParam(value = "department", required = false) Long departmentId,
      @RequestParam(value = "subject", required = false) Long subjectId,
      Model model) {
    List<Student> students;

    if (departmentId != null && subjectId != null) {
      students = studentService.findByDepartmentIdAndSubjectsId(departmentId, subjectId);
    }
    else if (departmentId != null) {
      students = studentService.findByDepartmentId(departmentId);
    }
    else if (subjectId != null) {
      students = studentService.findBySubjectId(subjectId);
    }
    else {
      students = studentService.getAllStudents();
    }

    model.addAttribute("students", students);
    model.addAttribute("departments",departmentService.getAllDepartments());
    model.addAttribute("subjects", subjectService.getAllSubjects());

    return "students";
  }

  @GetMapping("/newDepartmentForm")
  public String showNewDepartmentForm(Model model) {
    model.addAttribute("department", new Department());
    model.addAttribute("title", "Add Department");
    return "department_form";
  }

  @GetMapping("/editDepartmentForm/{departmentId}")
  public String editDepartmentForm(@PathVariable("departmentId") Long departmentId, Model model) {
    model.addAttribute("department", departmentService.getDepartmentById(departmentId));
    model.addAttribute("title", "Edit Department");
    return "department_form";
  }

  @PostMapping("/saveDepartment")
  public String saveDepartment(@ModelAttribute("department") Department department) {
    if (department.getId() == null) {
      departmentService.saveDepartment(department);
    } else {
      Department existingDepartment = departmentService.getDepartmentById(department.getId());
      existingDepartment.setName(department.getName());
      existingDepartment.setDescription(department.getDescription());
      departmentService.saveDepartment(existingDepartment);
    }
    return "redirect:/departments";
  }


  @GetMapping("/deleteDepartment/{id}")
  public String deleteDepartment(@PathVariable(value = "id") long id) {
    this.departmentService.deleteDepartmentById(id);
    return "redirect:/departments";
  }


  @GetMapping("/newTeacherForm")
  public String showNewTeacherForm(Model model) {
    model.addAttribute("teacher", new Teacher());
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("title", "Add Teacher");
    return "teacher_form";
  }

  @GetMapping("/editTeacherForm/{teacherId}")
  public String showEditTeacherForm(@PathVariable(value = "teacherId") Long teacherId,
      Model model) {
    model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("title", "Edit Teacher");
    return "teacher_form";
  }

  @PostMapping("saveTeacher")
  public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
    if (teacher.getId() == null) {
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
