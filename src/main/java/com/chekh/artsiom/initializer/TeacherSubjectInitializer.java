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
            List<Teacher> teacherList = teacherRepository.findAll();
            List<Subject> subjectList = subjectRepository.findAll();

            TeacherSubject teacherSubject1 = new TeacherSubject(teacherList.get(0), subjectList.get(0));
            teacherSubjectRepository.save(teacherSubject1);

            TeacherSubject teacherSubject2 = new TeacherSubject(teacherList.get(1), subjectList.get(1));
            teacherSubjectRepository.save(teacherSubject2);

            TeacherSubject teacherSubject3 = new TeacherSubject(teacherList.get(2), subjectList.get(2));
            teacherSubjectRepository.save(teacherSubject3);
        }
    }
}
