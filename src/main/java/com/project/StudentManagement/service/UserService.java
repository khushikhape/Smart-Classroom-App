package com.project.StudentManagement.service;

import com.project.StudentManagement.dao.UserDao;
import com.project.StudentManagement.entity.User;
import com.project.StudentManagement.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao dao;

    public User loginUser(LoginRequest request) {
        return dao.loginUser(request);
    }

    public User registerUser(User user) {
        return dao.registerUser(user);
    }

    public User getUserByName(String username) {
        return dao.getUserByName(username);
    }

    public List<User> getAllUser() {
        return dao.getAllUser();
    }

    public User updateUser(User user) {
        return dao.updateUser(user);
    }

    public String deleteUserById(String username) {
        return dao.deleteUserById(username);
    }

    public List<User> getAllAdmins() {
        return dao.getAllAdmins();
    }

    public List<User> getAllFaculties() {
        return dao.getAllFaculties();
    }
}
