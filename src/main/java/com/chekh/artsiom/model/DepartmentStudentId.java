package com.chekh.artsiom.model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DepartmentStudentId implements Serializable {

  @Column(name = "department_id")
  private Long departmentId;

  @Column(name = "student_id")
  private Long studentId;

  public DepartmentStudentId() {
  }

  public DepartmentStudentId(Long departmentId, Long studentId) {
    this.departmentId = departmentId;
    this.studentId = studentId;
  }

  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DepartmentStudentId that = (DepartmentStudentId) o;
    return Objects.equals(departmentId, that.departmentId) && Objects.equals(studentId, that.studentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(departmentId, studentId);
  }
}
