package com.pragma.powerup.factory;

import com.pragma.powerup.domain.model.OwnerModel;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;

import java.time.LocalDate;

public class UserTestData {
    public static OwnerModel getUserOwner(){
        OwnerModel ownerModel= new OwnerModel();
        ownerModel.setId(3L);
        ownerModel.setNombre("owner");
        ownerModel.setApellido("test_owner");
        ownerModel.setCelular("+573046102752");
        ownerModel.setFechaNacimiento(LocalDate.of(2001,10,10));
        ownerModel.setDocumentoIdentidad("1143405261");
        ownerModel.setEmail("owner_test@gmail.com");
        ownerModel.setClave("clave");
        ownerModel.setRol(getRolOwner());
        return ownerModel;
    }

    public static UserModel getUserEmployee(){
        UserModel userModel= new UserModel();
        userModel.setId(3L);
        userModel.setNombre("employee");
        userModel.setApellido("test_employee");
        userModel.setCelular("+573046352752");
        userModel.setDocumentoIdentidad("1123435261");
        userModel.setEmail("employee_test@gmail.com");
        userModel.setClave("clave");
        userModel.setRol(getRolEmployee());
        return userModel;
    }

    public static UserModel getUserClient(){
        UserModel userModel= new UserModel();
        userModel.setId(4L);
        userModel.setNombre("client");
        userModel.setApellido("test_client");
        userModel.setCelular("+573046352752");
        userModel.setDocumentoIdentidad("1123435261");
        userModel.setEmail("client_test@gmail.com");
        userModel.setClave("clave");
        userModel.setRol(getRolClient());
        return userModel;
    }

    public static RolModel getRolOwner(){
        RolModel rol = new RolModel();
        rol.setId(3L);
        rol.setNombre("PROPIETARIO");
        rol.setDescripcion("User al sistena, como Uribe a colombia");
        return  rol;
    }

    public static RolModel getRolEmployee(){
        RolModel rol = new RolModel();
        rol.setId(4L);
        rol.setNombre("EMPLEADO");
        rol.setDescripcion("User al sistema, como carrasquilla a Uribe");
        return  rol;
    }

    public static RolModel getRolClient(){
        RolModel rol = new RolModel();
        rol.setId(5L);
        rol.setNombre("CLIENTE");
        rol.setDescripcion("User al sistema, como los gringos a Pablito");
        return  rol;
    }

}
