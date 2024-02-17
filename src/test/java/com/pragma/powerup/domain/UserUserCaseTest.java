package com.pragma.powerup.domain;

import com.pragma.powerup.domain.model.EmployeeModel;
import com.pragma.powerup.domain.model.OwnerModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.spi.feignclient.IEmployeeFeignPort;
import com.pragma.powerup.domain.spi.feignclient.IRestaurantFeignPort;
import com.pragma.powerup.domain.spi.passwordencoder.IUserPasswordEncoderPort;
import com.pragma.powerup.domain.spi.token.IToken;
import com.pragma.powerup.domain.usecase.UserUseCase;
import com.pragma.powerup.factory.UserTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class UserUserCaseTest {
    @InjectMocks
    UserUseCase userUseCase;

    @Mock
    IUserPersistencePort userPersistencePort;

    @Mock
    IUserPasswordEncoderPort userPasswordEncoderPort;

    @Mock
    IToken token;

    @Mock
    IRestaurantFeignPort restaurantFeignPort;

    @Mock
    IEmployeeFeignPort employeeFeignPort;

    @Test
    void mustSaveAUserOwner() {
        OwnerModel ownerModel= UserTestData.getUserOwner();

        Mockito.when(token.getBearerToken()).thenReturn("Bearer token");
        Mockito.when(token.getUserAuthenticatedRol("Bearer token")).thenReturn("ADMIN");
        Mockito.when(userPasswordEncoderPort.encode(Mockito.any())).thenReturn("xfgghrtyrdgd");

        userUseCase.saveUser(ownerModel);

        Mockito.verify(userPasswordEncoderPort).encode("clave");
        Mockito.verify(userPersistencePort).saveUser(Mockito.any(OwnerModel.class));

        ArgumentCaptor<OwnerModel> argument = ArgumentCaptor.forClass(OwnerModel.class);
        Mockito.verify(userPersistencePort, Mockito.times(1)).saveUser(argument.capture());

        OwnerModel ownerModelSaved = argument.getValue();

        assertEquals(ownerModelSaved.getDocumentoIdentidad(), ownerModel.getDocumentoIdentidad());
        assertEquals(ownerModelSaved.getEmail(), ownerModel.getEmail());
        assertEquals(ownerModelSaved.getFechaNacimiento(), ownerModel.getFechaNacimiento());
    }

    @Test
    void mustSaveAUserEmployee() {
        UserModel userEmployee= UserTestData.getUserEmployee();

        Mockito.when(token.getBearerToken()).thenReturn("Bearer token");
        Mockito.when(token.getUserAuthenticatedRol("Bearer token")).thenReturn("PROPIETARIO");
        Mockito.when(userPasswordEncoderPort.encode(Mockito.any())).thenReturn("xfgghrtyrdgd");

        userUseCase.saveUser(userEmployee);

        Mockito.verify(userPasswordEncoderPort).encode("clave");
        Mockito.verify(userPersistencePort).saveUser(Mockito.any(UserModel.class));

        ArgumentCaptor<UserModel> argument = ArgumentCaptor.forClass(UserModel.class);
        Mockito.verify(userPersistencePort, Mockito.times(1)).saveUser(argument.capture());

        UserModel userModelSaved = argument.getValue();

        assertEquals(userModelSaved.getDocumentoIdentidad(), userEmployee.getDocumentoIdentidad());
        assertEquals(userModelSaved.getEmail(), userEmployee.getEmail());
    }

    @Test
    public void mustSaveRestaurantEmployee() {

        UserModel employee = new UserModel();
        employee.setEmail("test@employee.com");

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(1L);

        UserModel userModel = new UserModel();
        userModel.setId(1L);

        Mockito.when(token.getBearerToken()).thenReturn("Bearer token");
        Mockito.when(token.getUserAuthenticatedRol("Bearer token")).thenReturn("PROPIETARIO");
        Mockito.when(token.getUserAuthenticatedId("Bearer token")).thenReturn(2L);
        Mockito.when(restaurantFeignPort.getRestaurantByIdOwner(2L)).thenReturn(restaurantModel);
        Mockito.when(userPersistencePort.getUserByEmail(employee.getEmail())).thenReturn(userModel);

        userUseCase.saveRestaurantEmployee(employee);

        ArgumentCaptor<EmployeeModel> argument = ArgumentCaptor.forClass(EmployeeModel.class);
        Mockito.verify(employeeFeignPort, Mockito.times(1)).saveEmployee(argument.capture());

        EmployeeModel savedRestaurantEmployee = argument.getValue();
        assertEquals("1", savedRestaurantEmployee.getIdRestaurant());
        assertEquals("1", savedRestaurantEmployee.getIdEmployee());
    }

    @Test
    void mustSaveAUserClient() {
        UserModel client= UserTestData.getUserClient();

        Mockito.when(token.getBearerToken()).thenReturn(null);
        Mockito.when(userPasswordEncoderPort.encode(Mockito.any())).thenReturn("xfgghrtyrdgd");

        userUseCase.saveUser(client);

        Mockito.verify(userPasswordEncoderPort).encode("clave");
        Mockito.verify(userPersistencePort).saveUser(Mockito.any(UserModel.class));

        ArgumentCaptor<UserModel> argument = ArgumentCaptor.forClass(UserModel.class);
        Mockito.verify(userPersistencePort, Mockito.times(1)).saveUser(argument.capture());

        UserModel userModelSaved = argument.getValue();

        assertEquals(userModelSaved.getDocumentoIdentidad(), client.getDocumentoIdentidad());
        assertEquals(userModelSaved.getEmail(), client.getEmail());

    }


}
