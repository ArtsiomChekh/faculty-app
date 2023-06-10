package com.chekh.artsiom.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_subject")
public class TeacherSubject {

  @EmbeddedId
  private TeacherSubjectId id;

  @ManyToOne
  @MapsId("teacherId")
  private Teacher teacher;

  @ManyToOne
  @MapsId("subjectId")
  private Subject subject;

  public TeacherSubject() {
  }

  public TeacherSubject(Teacher teacher, Subject subject) {
    this.teacher = teacher;
    this.subject = subject;
    this.id = new TeacherSubjectId(teacher.getId(), subject.getId());
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public TeacherSubjectId getId() {
    return id;
  }

  public void setId(TeacherSubjectId id) {
    this.id = id;
  }
}
