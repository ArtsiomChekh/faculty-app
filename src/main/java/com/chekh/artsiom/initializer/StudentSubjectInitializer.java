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
            List<Student> students = studentRepository.findAll();
            List<Subject> subjects = subjectRepository.findAll();

            StudentSubject studentSubject1 = new StudentSubject(students.get(0), subjects.get(0));
            studentSubjectRepository.save(studentSubject1);

            StudentSubject studentSubject2 = new StudentSubject(students.get(1), subjects.get(1));
            studentSubjectRepository.save(studentSubject2);

            StudentSubject studentSubject3 = new StudentSubject(students.get(2), subjects.get(2));
            studentSubjectRepository.save(studentSubject3);
        }
    }
}
