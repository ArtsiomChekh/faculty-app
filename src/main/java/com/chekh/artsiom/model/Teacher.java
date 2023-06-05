package com.chekh.artsiom.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id")
  private Department department;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "teacher_subject",
      joinColumns = @JoinColumn(name = "teacher_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id"))
  private List<Subject> subjects = new ArrayList<>();

  public Teacher() {
  }

  public Teacher(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Teacher(String firstName, String lastName, Department department) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.department = department;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public List<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
  }

  public void addSubject(Subject subject) {
    if (subject != null) {
      subjects.add(subject);
      subject.addTeacher(this);
    }
  }
}