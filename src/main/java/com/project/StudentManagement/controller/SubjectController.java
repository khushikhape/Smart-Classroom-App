package com.project.StudentManagement.controller;

import com.project.StudentManagement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.StudentManagement.entity.Subject;
import java.util.List;

@RestController
@RequestMapping("/subject")
@CrossOrigin("http://localhost:4200")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/get-all-subjects")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/add-subject")
    @CrossOrigin(methods = RequestMethod.POST)
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @GetMapping("/get-subject-by-id/{id}")
    public Subject getSubjectById(@PathVariable long id) {
        return subjectService.getSubjectById(id);
    }

    @PutMapping("/update-subject")
    @CrossOrigin(methods = RequestMethod.PUT)
    public Subject updateSubject(@RequestBody Subject subjectDetails) {

        return subjectService.updateSubject(subjectDetails);
    }

    @DeleteMapping("/delete-subject/{id}")
    public String deleteSubject(@PathVariable long id) {
        return subjectService.deleteSubject(id);
    }
}
