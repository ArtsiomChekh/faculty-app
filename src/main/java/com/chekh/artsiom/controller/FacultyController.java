package com.chekh.artsiom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacultyController {

  @GetMapping("/homepage")
  public String showHomePage() {

    return "index";
  }
}
