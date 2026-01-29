package com.user.service.service;

import com.user.service.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User getUser(String userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(String userId);

}
