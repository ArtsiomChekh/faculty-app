package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.model.TeacherSubject;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import com.chekh.artsiom.repository.TeacherSubjectRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherSubjectInitializer {

    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @PostConstruct
    public void initialize() {
        if (teacherSubjectRepository.count() < 3) {
            List<Teacher> teachers = teacherRepository.findAll();
            List<Subject> subjects = subjectRepository.findAll();

            TeacherSubject teacherSubject1 = new TeacherSubject(teachers.get(0), subjects.get(0));
            teacherSubjectRepository.save(teacherSubject1);

            TeacherSubject teacherSubject2 = new TeacherSubject(teachers.get(1), subjects.get(1));
            teacherSubjectRepository.save(teacherSubject2);

            TeacherSubject teacherSubject3 = new TeacherSubject(teachers.get(2), subjects.get(2));
            teacherSubjectRepository.save(teacherSubject3);
        }
    }
}
