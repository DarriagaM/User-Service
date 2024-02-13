package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.spi.passwordencoder.IUserPasswordEncoderPort;
import com.pragma.powerup.domain.spi.token.IToken;
import org.mapstruct.control.MappingControl;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IUserPasswordEncoderPort userPasswordEncoderPort;
    private final IToken token;

    public UserUseCase(IUserPersistencePort userPersistencePort, IUserPasswordEncoderPort userPasswordEncoderPort, IToken token) {
        this.userPersistencePort = userPersistencePort;
        this.userPasswordEncoderPort = userPasswordEncoderPort;
        this.token = token;
    }

    @Override
    public void saveUser(UserModel user) {

        //3 es el codigo para crear un propietario, validamos si el user autenticado es admin.
        if(user.getRol().getId() == 3){
            validateRolAdmin();
            isLegalAge(user.getFechaNacimiento()); //prietario debe ser mayor de edad.
        }



        user.setClave(userPasswordEncoderPort.encode(user.getClave()));
        userPersistencePort.saveUser(user);
    }

    public void isLegalAge(LocalDate fechaNacimiento){
        LocalDate currentDate = LocalDate.now();
        if(currentDate.minusYears(18).isBefore(fechaNacimiento)){
            throw new DomainException("El administrador debe ser mayor de edad");
        }
    }

    public void validateRolAdmin(){
        String bearerToken = token.getBearerToken();
        String userAutenticatedRol = token.getUserAuthenticatedRol(bearerToken);
        if(!userAutenticatedRol.equals("ADMIN")){
            throw new DomainException("Solo el Admin puede crear Propietario");
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

    @Override
    public UserModel getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }
}
