package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.service.StudentService;
import com.chekh.artsiom.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/for-student")
    public String showForStudentPage(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "for-student";
    }
}
