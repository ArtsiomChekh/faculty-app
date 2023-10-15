package com.chekh.artsiom.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_subject")
public class StudentSubject {

  @EmbeddedId
  private StudentSubjectId id;

  @ManyToOne
  @MapsId("studentId")
  private Student student;

  @ManyToOne
  @MapsId("subjectId")
  private Subject subject;

  public StudentSubject() {
  }

  public StudentSubject(Student student, Subject subject) {
    this.student = student;
    this.subject = subject;
    this.id = new StudentSubjectId(student.getId(), subject.getId());
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public StudentSubjectId getId() {
    return id;
  }

  public void setId(StudentSubjectId id) {
    this.id = id;
  }
}




