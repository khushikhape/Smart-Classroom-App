package com.project.SmartClassroomApp.controller;

import com.project.SmartClassroomApp.entity.Student;
import com.project.SmartClassroomApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin("http://localhost:4200")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/get-all-students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/add-student")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/get-student-by-id/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/update-student")
    public Student updateStudent(@RequestBody Student studentDetails) {

        return studentService.updateStudent(studentDetails);
    }

    @DeleteMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable long id) {
        return studentService.deleteStudent(id);
    }
}
