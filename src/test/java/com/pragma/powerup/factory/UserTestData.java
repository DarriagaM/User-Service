package com.pragma.powerup.factory;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;

import java.time.LocalDate;
import java.util.Date;

public class UserTestData {
    public static UserModel getUser(){
        UserModel user= new UserModel();
        user.setId(3L);
        user.setNombre("owner");
        user.setApellido("test");
        user.setCelular("+573046102752");
        user.setFechaNacimiento(LocalDate.of(2001,10,10));
        user.setDocumentoIdentidad("1143405261");
        user.setEmail("test@gmail.com");
        user.setClave("password");
        user.setRol(getRol());
        return user;
    }

    public static RolModel getRol(){
        RolModel rol = new RolModel();
        rol.setId(3L);
        rol.setNombre("PROPIETARIO");
        rol.setDescripcion("User al sistena, como Uribe a colombia");
        return  rol;
    }

}
