package com.sut62.team07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;

@RestController
public class ExamScheduleController {
    @Autowired
    private final ExamScheduleRepository examScheduleRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CourseRepository courseRepository;

    ExamScheduleController(ExamScheduleRepository  examScheduleRepository) {
        this.examScheduleRepository = examScheduleRepository;
    }

    @GetMapping("/examSchedule")
    public Collection<ExamSchedule> examSchedules() {
        return examScheduleRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/examSchedule/{semester_id}/{academicYear}/{course_id}/{room_id}/{DATE}/{START_TIME}/{END_TIME}/{annotation}")
    public ExamSchedule newExamSchedule(ExamSchedule newExamSchedule,
                                        @PathVariable long semester_id,
                                        @PathVariable String academicYear,
                                        @PathVariable long course_id,
                                        @PathVariable long room_id,
                                        @PathVariable String DATE,
                                        @PathVariable String START_TIME,
                                        @PathVariable String END_TIME,
                                        @PathVariable String annotation) {

        Course course = courseRepository.findById(course_id).get();
        Room room = roomRepository.findById(room_id);
        Semester semester = semesterRepository.findById(semester_id);

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(DATE, formatDate);

        DateTimeFormatter formatStartTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(START_TIME, formatStartTime);

        DateTimeFormatter formatEndTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime endTime = LocalTime.parse(END_TIME, formatEndTime);

        newExamSchedule.setSemester(semester);
        newExamSchedule.setAcademicYear(academicYear);
        newExamSchedule.setCourse(course);
        newExamSchedule.setRoom(room);
        newExamSchedule.setDate(date);
        newExamSchedule.setStartTime(startTime);
        newExamSchedule.setEndTime(endTime);
        newExamSchedule.setAnnotation(annotation);

        return examScheduleRepository.save(newExamSchedule);

    }
}
