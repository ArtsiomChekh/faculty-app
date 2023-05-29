package com.chekh.artsiom.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "department")
  private Set<Subject> subjects;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "student_department",
      joinColumns = @JoinColumn(name = "department_id"),
      inverseJoinColumns = @JoinColumn(name = "student_id"))
  private List<Student> students = new ArrayList<>();

  public Department() {
  }

  public Department(String name, int studentCount, int teacherCount, String description) {
    this.name = name;
    this.studentCount = studentCount;
    this.teacherCount = teacherCount;
    this.description = description;
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

  // Сеттер для поля studentCount
  public void setStudentCount(int studentCount) {
    this.studentCount = studentCount;
  }

  // Метод для увеличения количества студентов на 1
  public void addStudent() {
    this.studentCount++;
  }

  public void setTeacherCount(int teacherCount) {
    this.teacherCount = teacherCount;
  }

  public void addTeacher() {
    this.teacherCount++;
  }

  // добавляем курс в множество subjects кафедры
  public void addSubject(Subject subject) {
    subjects.add(subject);
  }
  public Set<Subject> getSubjects(){
    return subjects;
  }


  public int getStudentCount() {
    return studentCount;
  }

  public int getTeacherCount() {
    return teacherCount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
