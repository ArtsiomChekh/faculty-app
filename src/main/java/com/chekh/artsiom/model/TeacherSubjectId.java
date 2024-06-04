package com.chekh.artsiom.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@Embeddable
public class TeacherSubjectId implements Serializable {

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "subject_id")
    private Long subjectId;

    public TeacherSubjectId() {
    }

    public TeacherSubjectId(Long teacherId, Long subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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
        TeacherSubjectId that = (TeacherSubjectId) o;
        return Objects.equals(teacherId, that.teacherId) && Objects.equals(subjectId,
                that.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, subjectId);
    }
}
