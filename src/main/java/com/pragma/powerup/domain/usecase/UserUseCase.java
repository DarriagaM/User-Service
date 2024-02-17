package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.*;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.spi.feignclient.IEmployeeFeignPort;
import com.pragma.powerup.domain.spi.feignclient.IRestaurantFeignPort;
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
    private final IRestaurantFeignPort restaurantFeignPort;
    private final IEmployeeFeignPort employeeFeignPort;

    public UserUseCase(IUserPersistencePort userPersistencePort,
                       IUserPasswordEncoderPort userPasswordEncoderPort,
                       IToken token,
                       IRestaurantFeignPort restaurantFeignPort,
                       IEmployeeFeignPort employeeFeignPort) {
        this.userPersistencePort = userPersistencePort;
        this.userPasswordEncoderPort = userPasswordEncoderPort;
        this.token = token;
        this.restaurantFeignPort = restaurantFeignPort;
        this.employeeFeignPort = employeeFeignPort;
    }

    @Override
    public void saveUser(UserModel user) {

        // si el user, es admin, se crea un propietario.
        if(validateRol("ADMIN")){
            OwnerModel ownerModel = (OwnerModel) user;
            ownerModel.setRol(new RolModel(3L,null,null)); //3L es id rol en la bd.
            isLegalAge(ownerModel.getFechaNacimiento());
        }

        //si el user es propietario se crea un empleado
        if(validateRol("PROPIETARIO")){
            user.setRol(new RolModel(4L,null,null));
        }

        //si no hay autenticacion, se crea un cliente
        if(token.getBearerToken() == null){
            user.setRol(new RolModel(5L,null,null));
        }

        user.setClave(userPasswordEncoderPort.encode(user.getClave()));
        userPersistencePort.saveUser(user);
    }

    @Override
    public void saveRestaurantEmployee(UserModel employee) {
        RestaurantModel restaurantModel = restaurantFeignPort
                                            .getRestaurantByIdOwner(token
                                                    .getUserAuthenticatedId(token.getBearerToken()));

        if(restaurantModel == null)
            throw new DomainException("User debe ser propietario de un restaurante");

        saveUser(employee);

        String idRestaurant = String.valueOf(restaurantModel.getId());
        String idUserEmployee = String.valueOf(userPersistencePort.getUserByEmail(employee.getEmail()).getId());

        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setIdEmployee(idUserEmployee);
        employeeModel.setIdRestaurant(idRestaurant);

        employeeFeignPort.saveEmployee(employeeModel);
    }

    public void isLegalAge(LocalDate fechaNacimiento){
        LocalDate currentDate = LocalDate.now();
        if(currentDate.minusYears(18).isBefore(fechaNacimiento)){
            throw new DomainException("El administrador debe ser mayor de edad");
        }
    }

    public boolean validateRol(String rol){
        String bearerToken = token.getBearerToken();
        if(bearerToken == null){
            return false;
        }
        String userAuthenticatedRol = token.getUserAuthenticatedRol(bearerToken);
        return userAuthenticatedRol.equals(rol);

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
