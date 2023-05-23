package com.chekh.artsiom.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id")
  private Department department;

  @ManyToOne
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
  private Set<Student> students = new HashSet<>();

  public Subject() {
  }

  public Subject(String name, Department department, Teacher teacher) {
    this.name = name;
    this.department = department;
    this.teacher = teacher;
  }

  public Subject(String name, Department department) {
    this.name = name;
    this.department = department;
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

  public Department getDepartment() {
    return department;
  }



  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public void setDepartment(Department department) {
    this.department = department;
    department.getSubjects().add(this);
  }


}
