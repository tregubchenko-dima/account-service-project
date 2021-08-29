package com.web.service;

import com.web.model.Role;
import com.web.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);
    void addRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
