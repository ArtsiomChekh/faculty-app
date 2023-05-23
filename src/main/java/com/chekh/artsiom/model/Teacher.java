package com.chekh.artsiom.model;

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

  @OneToMany(mappedBy = "teacher")
  private List<Subject> subjects;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id")
  private Department department;

  public Teacher() {
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

  // добавляем предмет в множество subjects преподавателя
  public void addSubject(Subject subject) {
    subject.setTeacher(this);
    subjects.add(subject);
  }

  public List<Subject> getSubjects() {
    return subjects;
  }
}