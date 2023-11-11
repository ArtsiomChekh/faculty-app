package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.model.StudentSubject;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.repository.StudentRepository;
import com.chekh.artsiom.repository.StudentSubjectRepository;
import com.chekh.artsiom.repository.SubjectRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentSubjectInitializer {
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @PostConstruct
    public void initialize() {
        if (studentSubjectRepository.count() < 3) {
            List<Student> studentList = studentRepository.findAll();
            List<Subject> subjectList = subjectRepository.findAll();

            StudentSubject studentSubject1 = new StudentSubject(studentList.get(0), subjectList.get(0));
            studentSubjectRepository.save(studentSubject1);

            StudentSubject studentSubject2 = new StudentSubject(studentList.get(1), subjectList.get(1));
            studentSubjectRepository.save(studentSubject2);

            StudentSubject studentSubject3 = new StudentSubject(studentList.get(2), subjectList.get(2));
            studentSubjectRepository.save(studentSubject3);
        }
    }
}
