package com.project.StudentManagement.dao;

import com.project.StudentManagement.model.LoginRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.project.StudentManagement.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private SessionFactory factory;

    public User loginUser(LoginRequest request) {
        Session session = null;
        User user = null;
        try {
            session = factory.openSession();
            user = session.find(User.class, request.getUsername());
            if (user != null) {
                if (user.getPassword().equals(request.getPassword())) {
                    return user;
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteUserById(String username) {
        Session session = null;
        String msg = null;
        try {
            session = factory.openSession();
            User user = session.find(User.class, username);
            session.remove(user);
            session.beginTransaction().commit();
            msg = "deleted";

        } catch (Exception e) {
            msg = null;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return msg;
    }

    public User updateUser(User user) {
        Session session = null;

        try {
            session = factory.openSession();
            session.merge(user);
            session.beginTransaction().commit();
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUser() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            cq.select(root);

            List<User> list = session.createQuery(cq).getResultList();
            transaction.commit();
            return list;


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    public User getUserByName(String username) {
        Session session = null;
        User user = null;
        try {
            session = factory.openSession();
            user = session.find(User.class, username);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
        return user;
    }

    public User registerUser(User user) {
        Session session = null;
        User user2 = null;
        try {
            session = factory.openSession();
            user2 = session.find(User.class, user.getUsername());
            if (user2 == null) {
                session.persist(user);
                session.beginTransaction().commit();
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
        return null;
    }

    public List<User> getAllAdmins() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            cq.select(root).where(cb.equal(root.get("role"),"admin"));

            List<User> list = session.createQuery(cq).getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    public List<User> getAllFaculties() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            cq.select(root).where(cb.equal(root.get("role"),"faculty"));

            List<User> list = session.createQuery(cq).getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }
}
