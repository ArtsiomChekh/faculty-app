package com.chekh.artsiom.model;

import jakarta.persistence.*;

@Entity
@Table(name = "department_student")
public class DepartmentStudent {

  @EmbeddedId
  private DepartmentStudentId id;

  @ManyToOne
  @MapsId("departmentId")
  @JoinColumn(name = "department_id")
  private Department department;

  @ManyToOne
  @MapsId("studentId")
  @JoinColumn(name = "student_id")
  private Student student;

  public DepartmentStudent() {
  }

  public DepartmentStudent(Department department, Student student) {
    this.department = department;
    this.student = student;
    this.id = new DepartmentStudentId(department.getId(), student.getId());
  }

  public DepartmentStudentId getId() {
    return id;
  }

  public void setId(DepartmentStudentId id) {
    this.id = id;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }
}