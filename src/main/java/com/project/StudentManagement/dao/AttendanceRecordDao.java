package com.project.StudentManagement.dao;

import com.project.StudentManagement.entity.AttendanceRecord;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttendanceRecordDao {
    @Autowired
    private SessionFactory factory;

    public List<AttendanceRecord> getAllAttendanceRecords() {
        Session session = null;
        List<AttendanceRecord> list = null;
        try {
            session = factory.openSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AttendanceRecord> cq = cb.createQuery(AttendanceRecord.class);
            Root<AttendanceRecord> root = cq.from(AttendanceRecord.class);

            cq.select(root);

            return session.createQuery(cq).getResultList();
        } finally {
            if (session != null) session.close();
        }
    }

    public List<AttendanceRecord> getAllAttendanceRecords(String date, long subjectId) {
        Session session = null;
        List<AttendanceRecord> list = null;
        try {
            session = factory.openSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AttendanceRecord> cq = cb.createQuery(AttendanceRecord.class);
            Root<AttendanceRecord> root = cq.from(AttendanceRecord.class);

            Predicate datePredicate = cb.equal(root.get("date"), date);
            Predicate subjectPredicate = cb.equal(root.get("subject").get("id"), subjectId);

            cq.select(root).where(cb.and(datePredicate, subjectPredicate));

            return session.createQuery(cq).getResultList();
        } finally {
            if (session != null) session.close();
        }
    }

    public AttendanceRecord saveAttendance(AttendanceRecord attendanceRecord) {
        Session session = null;
        AttendanceRecord record = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(attendanceRecord);
            transaction.commit();
            record = attendanceRecord;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return record;
    }
}
