package com.chekh.artsiom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping(value = {"/","/home"})
  public String showHomePage() {
    // return the view name to be rendered by the view resolver for the home page
    return "home";
  }

}
