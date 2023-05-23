package com.chekh.artsiom.model;

import java.util.Collection;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "student_count")
  private int studentCount;

  @Column(name = "teacher_count")
  private int teacherCount;

  @OneToMany(mappedBy = "department")
  private Set<Subject> subjects;

  public Department() {
  }

  public Department(String name, int studentCount, int teacherCount) {
    this.name = name;
    this.studentCount = studentCount;
    this.teacherCount = teacherCount;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // добавляем курс в множество subjects кафедры
  public void addSubject(Subject subject) {
    subjects.add(subject);
  }
  public Set<Subject> getSubjects(){
    return subjects;
  }


}
