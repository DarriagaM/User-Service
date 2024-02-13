package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.UserModel;
import org.apache.catalina.User;

import java.util.List;

public interface IUserServicePort {
    void saveUser(UserModel user);
    UserModel getUserById(Long id);
    boolean existsUserById(Long id);
    List<UserModel> getAllUser();
    void deleteUserById(Long id);
    UserModel getUserByEmail(String email);
}
