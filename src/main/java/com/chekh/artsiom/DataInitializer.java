package com.chekh.artsiom;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.repository.DepartmentRepository;
import com.chekh.artsiom.repository.StudentRepository;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DataInitializer implements CommandLineRunner {

  @Autowired
  DepartmentRepository departmentRepository;

  @Autowired
  SubjectRepository subjectRepository;

  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  StudentRepository studentRepository;

  @Override
  public void run(String... args) throws Exception {

    if (departmentRepository.count() == 0) {
      Department department1 = new Department("Кафедра вычислительных методов и программирования",
          "Кафедра вычислительных методов и программирования занимается исследованием и разработкой методов и алгоритов для решения различных задач в области вычислительной математики, науки о данных,скусствного интеллекта и других областей. Также кафедра обучает студентов программированию на различных языках программирования и применению вычислительных методов для решения задач в различных областях науки и техники.");
      Department department2 = new Department("Кафедра гуманитарных дисциплин",
          "Кафедра гуманитарных дисциплин занимается изучением и преподаванием гуманитарных наук, таких как философия, история, социология, психология, культурология и другие. Кроме того, кафедра также обучает студентов навыкам коммуникации, критическому мышлению, анализу и интерпретации текстов, что помогает им стать более компетентными и эрудированными специалистами в своей области.");
      Department department3 = new Department("Кафедра интеллектуальных информационных технологий",
          "Кафедра интеллектуальных информационных технологий занимается исследованием и разработкой новых методов и техногий в области искусственного интеллекта, машинного обучения, обработки естественного яыка и других смежных областей. Кроме того, кафедра обучает студентов применению этих технологий решения различных задач в области информационных технологий, бизнеса, медицины и других областей.");

    }


  }
}
