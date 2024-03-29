package com.chekh.artsiom.controller;

import com.chekh.artsiom.model.Schedule;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.service.ScheduleService;
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
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ScheduleService scheduleService;

   @GetMapping("student/for-student")
    public String showForStudentPage(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Schedule> schedules = scheduleService.getAllSchedules();

        model.addAttribute("subjects", subjects);
        model.addAttribute("schedules", schedules);
        return "for-student";
    }
}
