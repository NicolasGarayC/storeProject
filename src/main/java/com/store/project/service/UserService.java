package com.store.project.service;

import com.store.project.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(Integer id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUserById(Integer id);
}
