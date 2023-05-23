package com.chekh.artsiom.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

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

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
  private Set<Student> students = new HashSet<>();

  public Course() {
  }

  public Course(String name, Department department, Teacher teacher) {
    this.name = name;
    this.department = department;
    this.teacher = teacher;
  }

  public Course(String name, Department department) {
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
    department.getCourses().add(this);
  }


}
