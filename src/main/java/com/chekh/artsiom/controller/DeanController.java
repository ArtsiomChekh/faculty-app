package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.service.DepartmentService;
import com.chekh.artsiom.service.StudentService;
import com.chekh.artsiom.service.SubjectService;
import com.chekh.artsiom.service.TeacherService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
  public String getDepartmentsPage(Model model) {
    Map<Department, Long> departmentStudentCountMap = departmentService.getDepartmentsStudentCount();
    model.addAttribute("departmentStudentCountMap", departmentStudentCountMap);
    return "departments";
  }

  @GetMapping("/departments/sorted-by-num-students")
  public String getAllDepartmentsSortedByNumStudents(Model model) {
    Map<Department, Long> departmentStudentCountMap = departmentService.getDepartmentsStudentCount();
    List<Long> studentCounts = new ArrayList<>(departmentStudentCountMap.values());
    List<String> departments = new ArrayList<>();

    for (Department department : departmentStudentCountMap.keySet()) {
      departments.add(department.getName());
    }

    model.addAttribute("departments", departments);
    model.addAttribute("studentCounts", studentCounts);

    return "departments";
  }

  @GetMapping("/departments/sorted-by-num-teachers")
  public String getAllDepartmentsSortedByNumTeachers(Model model) {
    List<Department> departments = departmentService.getAllDepartmentsSortedByTeachers();
    model.addAttribute("departments", departments);
    return "departments";
  }

  @GetMapping("/newDepartmentForm")
  public String showNewDepartmentForm(Model model) {
    model.addAttribute("department", new Department());
    model.addAttribute("title", "Добавить Кафедру");
    return "department_form";
  }

  @GetMapping("/editDepartmentForm/{departmentId}")
  public String editDepartmentForm(@PathVariable("departmentId") Long departmentId, Model model) {
    model.addAttribute("department", departmentService.getDepartmentById(departmentId));
    model.addAttribute("title", "Редактировать Кафедру");
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

  @GetMapping("/departmentInfo/{departmentId}")
  public String getDepartmentInfo(@PathVariable("departmentId") Long departmentId, Model model) {
    Department department = departmentService.getDepartmentById(departmentId);
    List<Subject> subjects = subjectService.findByDepartmentId(departmentId);
    model.addAttribute("department", departmentService.getDepartmentById(departmentId));
    model.addAttribute("subjects", subjectService.findByDepartmentId(departmentId));
    model.addAttribute("students", studentService.findByDepartmentId(departmentId));
    model.addAttribute("teachers", teacherService.findByDepartmentId(departmentId));
    return "department_info";
  }

  @GetMapping("/teachers")
  public String getTeachersPage(@RequestParam(value = "department", required = false) Long departmentId,
      @RequestParam(value = "subject", required = false) Long subjectId, Model model) {

    List<Teacher> teachers;

    if (departmentId != null && subjectId != null) {
      teachers = teacherService.findByDepartmentIdAndSubjectId(departmentId, subjectId);
    } else if (departmentId != null) {
      teachers = teacherService.findByDepartmentId(departmentId);
    } else if (subjectId != null) {
      teachers = teacherService.findBySubjectId(subjectId);
    } else {
      teachers = teacherService.getAllTeachers();
    }

    List<List<Subject>> subjects = new ArrayList<>();

    for (Teacher teacher : teachers) {
      List<Subject> teacherSubjects = teacherService.getSubjectsByTeacherId(teacher.getId());
      subjects.add(teacherSubjects);
    }

    model.addAttribute("teachers", teachers);
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjects);
    model.addAttribute("allSubjects", subjectService.getAllSubjects());

    return "teachers";
  }

  @GetMapping("/newTeacherForm")
  public String showNewTeacherForm(Model model) {
    model.addAttribute("teacher", new Teacher());
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("teacherSubjects", new ArrayList<Subject>());
    model.addAttribute("title", "Добавить Учителя");
    return "teacher_form";
  }

  @GetMapping("/editTeacherForm/{teacherId}")
  public String showEditTeacherForm(@PathVariable(value = "teacherId") Long teacherId,
      Model model) {

    List<Subject> teacherSubjects = teacherService.getSubjectsByTeacherId(teacherId);

    model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("teacherSubjects", teacherSubjects);
    model.addAttribute("title", "Редактировать Учителя");
    return "teacher_form";
  }

  @PostMapping("saveTeacher")
  public String saveTeacherWithSubjects(@ModelAttribute("teacher") Teacher teacher,
      @RequestParam(name = "subjectIds", required = false) List<Long> subjectIds) {

    if (subjectIds == null) {
      teacherService.saveTeacher(teacher);
    } else {
      teacherService.saveTeacherWithSubjects(teacher, subjectIds);
    }
    return "redirect:/teachers";
  }

  @GetMapping("/deleteTeacher/{id}")
  public String deleteTeacher(@PathVariable(value = "id") long id) {
    teacherService.deleteTeacherById(id);
    return "redirect:/teachers";
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
      subjects = subjectService.findByDepartmentId(departmentId);
    } else {
      subjects = subjectService.getAllSubjects();
    }

    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjects);
    model.addAttribute("selectedDepartmentId", departmentId);

    return "subjects";
  }

  @GetMapping("/newSubjectForm")
  public String showNewSubjectForm(Model model) {
    model.addAttribute("subject", new Subject());
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("title", "Добавить Предмет");
    return "subject_form";
  }

  @GetMapping("/editSubjectForm/{subjectId}")
  public String showEditSubjectForm(@PathVariable("subjectId") Long subjectId, Model model) {
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("subject", subjectService.getSubjectById(subjectId));
    model.addAttribute("title", "Редактировать Предмет");
    return "subject_form";
  }

  @PostMapping("/saveSubject")
  public String saveSubject(@ModelAttribute("subject") Subject subject) {

    Department department = subject.getDepartment();

    if (department != null && department.getId() == null) {
      department = null;
    }
    subject.setDepartment(department);
    subjectService.saveSubject(subject);

    return "redirect:/subjects";
  }

  @GetMapping("/deleteSubject/{id}")
  public String deleteSubject(@PathVariable(value = "id") long id) {
    subjectService.deleteSubjectById(id);
    return "redirect:/subjects";
  }

  @GetMapping("/students")
  public String getStudentsPage(
      @RequestParam(value = "department", required = false) Long departmentId,
      @RequestParam(value = "subject", required = false) Long subjectId,
      Model model) {

    List<Student> students;

    if (departmentId != null && subjectId != null) {
      students = studentService.findByDepartmentIdAndSubjectId(departmentId, subjectId);
    } else if (departmentId != null) {
      students = studentService.findByDepartmentId(departmentId);
    } else if (subjectId != null) {
      students = studentService.findBySubjectId(subjectId);
    } else {
      students = studentService.getAllStudents();
    }

    List<List<Subject>> subjects = new ArrayList<>();

    for (Student student : students) {
      List<Subject> studentSubjects = studentService.getSubjectsByStudentId(student.getId());
      subjects.add(studentSubjects);
    }

    List<List<Department>> departments = new ArrayList<>();

    for (Student student : students) {
      List<Department> studentDepartments = studentService.getDepartmentsByStudentId(
          student.getId());
      departments.add(studentDepartments);
    }

    model.addAttribute("students", students);
    model.addAttribute("departments", departments);
    model.addAttribute("allDepartments", departmentService.getAllDepartments());
    model.addAttribute("subjects", subjects);
    model.addAttribute("allSubjects", subjectService.getAllSubjects());

    return "students";
  }

  @GetMapping("/newStudentForm")
  public String showNewStudentForm(Model model) {
    model.addAttribute("student", new Student());
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("studentSubjects", new ArrayList<Subject>());
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("studentDepartments", new ArrayList<Department>());
    model.addAttribute("title", "Добавить Студента");
    return "student_form";
  }

  @GetMapping("/editStudentForm/{studentId}")
  public String showEditStudentForm(@PathVariable("studentId") Long studentId, Model model) {
    model.addAttribute("student", studentService.getStudentById(studentId));
    model.addAttribute("subjects", subjectService.getAllSubjects());
    model.addAttribute("departments", departmentService.getAllDepartments());
    model.addAttribute("studentSubjects", studentService.getSubjectsByStudentId(studentId));
    model.addAttribute("studentDepartments", studentService.getDepartmentsByStudentId(studentId));
    model.addAttribute("title", "Редактировать Студента");
    return "student_form";
  }

  @PostMapping("saveStudent")
  public String saveStudent(@ModelAttribute("student") Student student,
      @RequestParam(name = "subjectIds", required = false) List<Long> subjectIds,
      @RequestParam(name = "departmentIds", required = false) List<Long> departmentIds) {
    studentService.saveStudent(student, subjectIds, departmentIds);
    return "redirect:/students";
  }

  @GetMapping("/deleteStudent/{id}")
  public String deleteStudent(@PathVariable("id") long id) {
    studentService.deleteStudentById(id);
    return "redirect:/students";
  }

}
