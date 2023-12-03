package com.chekh.artsiom.model;


import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@Embeddable
public class StudentSubjectId implements Serializable {

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "subject_id")
    private Long subjectId;

    public StudentSubjectId() {
    }

    public StudentSubjectId(Long studentId, Long subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentSubjectId that = (StudentSubjectId) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(subjectId,
                that.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, subjectId);
    }
}
