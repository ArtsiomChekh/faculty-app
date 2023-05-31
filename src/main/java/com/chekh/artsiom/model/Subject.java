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

  public void setTeachers(Set<Teacher> teachers) {
    this.teachers = teachers;
  }

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
  private Set<Teacher> teachers = new HashSet<>();

  public Subject() {
  }

  public Subject(String name) {
    this.name = name;
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

  public void setDepartment(Department department) {
    this.department = department;
    if (department != null) {
      department.getSubjects().add(this);
    }
  }

  public Set<Teacher> getTeachers() {
    return teachers;
  }


  public void addTeacher(Teacher teacher) {
    teachers.add(teacher);
  }
}