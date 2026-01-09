package com.project.SmartClassroomApp.controller;

import com.project.SmartClassroomApp.entity.AttendanceRecord;
import com.project.SmartClassroomApp.entity.Student;
import com.project.SmartClassroomApp.entity.Subject;
import com.project.SmartClassroomApp.entity.User;
import com.project.SmartClassroomApp.model.AttendanceRecordRequest;
import com.project.SmartClassroomApp.service.AttendanceRecordService;
import com.project.SmartClassroomApp.service.StudentService;
import com.project.SmartClassroomApp.service.SubjectService;
import com.project.SmartClassroomApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin("http://localhost:4200")
public class AttendanceController {
    @Autowired
    private AttendanceRecordService attendanceRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/get-all-attendance-records")
    public List<AttendanceRecord> getAllAttendanceRecords() {
        return attendanceRecordService.getAllAttendanceRecords();
    }

    @GetMapping("/get-attendance-by-date-subjet/{date}/{subjectId}")
    public List<AttendanceRecord> getAllAttendanceRecords(@PathVariable String date,@PathVariable long subjectId){

        return attendanceRecordService.getAllAttendanceRecords(date,subjectId);

    }

    @PostMapping("/take-attendance")
    public AttendanceRecord createAttendanceRecord(@RequestBody AttendanceRecordRequest request) {
        User user = userService.getUserByName(request.getUsername());
        Subject subject = subjectService.getSubjectById(request.getSubjectId());
        List<Student> students = studentService.getAllStudentsById(request.getStudentIds());

        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setUser(user);
        attendanceRecord.setSubject(subject);
        attendanceRecord.setDate(request.getDate());
        attendanceRecord.setTime(request.getTime());
        attendanceRecord.setStudents(students);
        attendanceRecord.setNumberOfStudents(request.getStudentIds().size());

        System.out.println(attendanceRecord);

        return attendanceRecordService.saveAttendance(attendanceRecord);
    }
}
