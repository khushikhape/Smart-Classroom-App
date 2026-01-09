package com.project.StudentManagement.dao;

import com.project.StudentManagement.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {
    @Autowired
    private SessionFactory factory;

    public List<Student> getAllStudentsById(List<Long> studentIds) {
        Session session = null;
        List<Student> students = null;
        try {
            session = factory.openSession();
            return session.createQuery(
                            "FROM Student s WHERE s.id IN :ids",
                            Student.class
                    ).setParameter("ids", studentIds)
                    .getResultList();

        } finally {
            if (session != null) session.close();
        }
    }

    public List<Student> getAllStudents() {
        Session session = null;
        List<Student> list = null;
        try {
            session = factory.openSession();

            return session.createQuery("FROM Student", Student.class)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    public Student createStudent(Student student) {
        Session session = null;
        Student s = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            s = student;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return s;
    }

    public Student getStudentsById(long id) {
        Session session = null;
        Student student = null;
        try {
            session = factory.openSession();
            student = session.find(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }

    public Student updateStudent(Student studentDetails) {
        Session session = null;
        Student s = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(studentDetails);
            transaction.commit();
            s = studentDetails;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return s;
    }

    public String deleteStudent(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            int rows = session.createMutationQuery(
                            "DELETE FROM Student s WHERE s.id = :id"
                    ).setParameter("id", id)
                    .executeUpdate();

            transaction.commit();

            return rows > 0 ? "Deleted !!" : "Student not found";

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error while deleting";
        } finally {
            if (session != null) session.close();
        }
    }



}
