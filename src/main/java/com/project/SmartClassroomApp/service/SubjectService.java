package com.project.SmartClassroomApp.service;

import com.project.SmartClassroomApp.dao.SubjectDao;
import com.project.SmartClassroomApp.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectDao dao;

    public Subject getSubjectById(long subjectId) {

        return dao.getSubjectById(subjectId);
    }

    public List<Subject> getAllSubjects() {

        return dao.getAllSubjects();
    }

    public Subject createSubject(Subject subject) {

        return dao.createSubject(subject);
    }

    public Subject updateSubject(Subject subjectDetails) {

        return dao.updateSubject(subjectDetails);
    }

    public String deleteSubject(long id) {

        return dao.deleteSubject(id);
    }
}
