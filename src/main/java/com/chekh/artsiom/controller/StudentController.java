package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.service.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class StudentController {

  @Autowired
  private SubjectService subjectService;

  @GetMapping
  public String getAllSubjects(Model model) {
    List<Subject> subjects = subjectService.findAll();
    model.addAttribute("subjects", subjects);
    return "subjects";
  }



}
