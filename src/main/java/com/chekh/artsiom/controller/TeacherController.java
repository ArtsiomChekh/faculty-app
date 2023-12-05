package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.service.DepartmentService;
import com.chekh.artsiom.service.SubjectService;
import com.chekh.artsiom.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/for-teacher")
    public String showForTeacherPage(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("subjects", subjects);
        model.addAttribute("departments", departments);
        return "for-teacher";
    }

    @PostMapping("/teach/{subjectId}")
    public String teachSubject(@PathVariable("subjectId") Long subjectId) {
        return "redirect:/for-teacher";
    }
}
