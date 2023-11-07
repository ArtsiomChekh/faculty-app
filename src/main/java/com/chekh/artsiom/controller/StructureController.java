package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.service.DepartmentService;
import com.chekh.artsiom.service.SubjectService;
import com.chekh.artsiom.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StructureController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = {"/structure"})
    public String showStructurePage (Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "structure";
    }
}
