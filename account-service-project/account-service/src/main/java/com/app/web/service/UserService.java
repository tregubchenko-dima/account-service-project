package com.app.web.service;

import com.app.web.model.Role;
import com.app.web.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);
    void addRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
