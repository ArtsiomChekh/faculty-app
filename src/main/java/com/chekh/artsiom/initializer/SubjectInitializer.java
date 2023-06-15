package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.repository.SubjectRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectInitializer {

  @Autowired
  private SubjectRepository subjectRepository;

  @PostConstruct
  public void initialize() {
    if (subjectRepository.count() < 3) {
      Subject subject1 = new Subject();
      subject1.setName("Вычислительные машины, системы и сети");
      subjectRepository.save(subject1);

      Subject subject2 = new Subject();
      subject2.setName("Информационные системы и технологии (в экономике)");
      subjectRepository.save(subject2);

      Subject subject3 = new Subject();
      subject3.setName("Информационная безопасность");
      subjectRepository.save(subject3);
    }
  }


}
