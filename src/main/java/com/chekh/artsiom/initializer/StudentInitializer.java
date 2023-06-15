package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.repository.StudentRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentInitializer {

  @Autowired
  private StudentRepository studentRepository;

  @PostConstruct
  public void initialize() {
    if (studentRepository.count() < 3) {
      Student student1 = new Student();
      student1.setFirstName("Андрей");
      student1.setLastName("Маркович");
      studentRepository.save(student1);

      Student student2 = new Student();
      student2.setFirstName("Кирилл");
      student2.setLastName("Петрович");
      studentRepository.save(student2);

      Student student3 = new Student();
      student3.setFirstName("Николай");
      student3.setLastName("Смых");
      studentRepository.save(student3);
    }
  }
}
