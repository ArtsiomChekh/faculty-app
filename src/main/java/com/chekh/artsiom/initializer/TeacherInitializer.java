package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.TeacherRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherInitializer {

  @Autowired
  private TeacherRepository teacherRepository;


  @PostConstruct
  public void initialize() {
    if (teacherRepository.count() < 3) {
      Teacher teacher1 = new Teacher();
      teacher1.setFirstName("Евгений");
      teacher1.setLastName("Болан");
      teacherRepository.save(teacher1);

      Teacher teacher2 = new Teacher();
      teacher2.setFirstName("Сергей");
      teacher2.setLastName("Сомов");
      teacherRepository.save(teacher2);

      Teacher teacher3 = new Teacher();
      teacher3.setFirstName("Геннадий");
      teacher3.setLastName("Никуль");
      teacherRepository.save(teacher3);
    }
  }

}
