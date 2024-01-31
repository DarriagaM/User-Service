package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.spi.passwordencoder.IUserPasswordEncoderPort;

import java.time.LocalDate;
import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IUserPasswordEncoderPort userPasswordEncoderPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IUserPasswordEncoderPort userPasswordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.userPasswordEncoderPort = userPasswordEncoderPort;
    }

    @Override
    public void saveUser(UserModel user) {
        isLegalAge(user.getFechaNacimiento());
        user.setClave(userPasswordEncoderPort.encode(user.getClave()));
        userPersistencePort.saveUser(user);
    }

    public void isLegalAge(LocalDate fechaNacimiento){
        LocalDate currentDate = LocalDate.now();
        if(currentDate.minusYears(18).isBefore(fechaNacimiento)){
            throw new DomainException("El administrador debe ser mayor de edad");
        }
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
