package com.project.StudentManagement.dao;

import com.project.StudentManagement.entity.Subject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectDao {
    @Autowired
    private SessionFactory factory;

    public Subject getSubjectById(long subjectId) {
        Session session = null;
        Subject subject = null;
        try {
            session = factory.openSession();
            subject = session.find(Subject.class, subjectId);
        } finally {
            if (session != null) session.close();
        }
        return subject;
    }

    public List<Subject> getAllSubjects() {
        Session session = null;
        List<Subject> list = null;
        try {
            session = factory.openSession();

            CriteriaBuilder criteria = session.getCriteriaBuilder();
            CriteriaQuery<Subject> criteriaQuery = criteria.createQuery(Subject.class);
            Root<Subject> root = criteriaQuery.from(Subject.class);

            criteriaQuery.select(root);

            return session.createQuery(criteriaQuery).getResultList();
        }
        finally {
            if (session != null) session.close();
        }
        
    }

    public Subject createSubject(Subject subject) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder criteria = session.getCriteriaBuilder();
            CriteriaQuery<Subject> criteriaQuery = criteria.createQuery(Subject.class);
            Root<Subject> root = criteriaQuery.from(Subject.class);

            criteriaQuery.select(root).where(criteria.equal(root.get("subjectname"),subject.getName()));

            List<Subject> list = session.createQuery(criteriaQuery).getResultList();

            if (list.isEmpty()) {
                session.persist(subject);
                transaction.commit();
                return subject;
            }else{
                return list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    public Subject updateSubject(Subject subjectDetails) {
        Session session = null;
        Subject sub = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(subjectDetails);
            transaction.commit();
            sub = subjectDetails;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return sub;
    }

    public String deleteSubject(long id) {
        Session session = null;
        String msg = null;
        try {
            session = factory.openSession();
            Subject subject = session.find(Subject.class, id);
            session.remove(subject);
            session.beginTransaction().commit();
            msg = "deleted";

        } catch (Exception e) {
            msg = null;
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return msg;
    }
}
