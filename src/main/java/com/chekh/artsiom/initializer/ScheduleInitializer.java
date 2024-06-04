package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Schedule;
import com.chekh.artsiom.model.Subject;
import com.chekh.artsiom.model.Teacher;
import com.chekh.artsiom.repository.ScheduleRepository;
import com.chekh.artsiom.repository.SubjectRepository;
import com.chekh.artsiom.repository.TeacherRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class ScheduleInitializer {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @PostConstruct
    public void initialize() {
        if (scheduleRepository.count() < 3) {
            List<Subject> subjects = subjectRepository.findAll();
            List<Teacher> teachers = teacherRepository.findAll();

            Schedule schedule1 = new Schedule();
            schedule1.setSubject(subjects.get(0));
            schedule1.setTeacher(teachers.get(0));
            schedule1.setWeekDay(1);
            schedule1.setStartTime(LocalTime.of(8, 0));
            schedule1.setEndTime(LocalTime.of(9, 45));
            scheduleRepository.save(schedule1);

            Schedule schedule2 = new Schedule();
            schedule2.setSubject(subjects.get(1));
            schedule2.setTeacher(teachers.get(1));
            schedule2.setWeekDay(2);
            schedule2.setStartTime(LocalTime.of(10, 0));
            schedule2.setEndTime(LocalTime.of(11, 45));
            scheduleRepository.save(schedule2);

            Schedule schedule3 = new Schedule();
            schedule3.setSubject(subjects.get(2));
            schedule3.setTeacher(teachers.get(2));
            schedule3.setWeekDay(3);
            schedule3.setStartTime(LocalTime.of(12, 0));
            schedule3.setEndTime(LocalTime.of(13, 45));
            scheduleRepository.save(schedule3);
        }
    }
}
