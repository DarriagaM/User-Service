package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(UserModel user) {
        userPersistencePort.saveUser(user);
    }

    @Override
    public UserModel getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    @Override
    public boolean existsUserById(Long id) {
        return userPersistencePort.existsUserById(id);
    }

    @Override
    public List<UserModel> getAllUser() {
        return userPersistencePort.getAllUser();
    }

    @Override
    public void deleteUserById(Long id) {
        userPersistencePort.deleteUserById(id);
    }
}
