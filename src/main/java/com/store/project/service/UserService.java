package com.store.project.service;

import com.store.project.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {


    void saveUser(User user);


    Optional<User> getUserById(Integer id);

    List<User> getAllUsers();

    String updateUser(User user);

    void deleteUserById(Integer id);

    String validarContrasena(String nuevaContrasenia);
}
