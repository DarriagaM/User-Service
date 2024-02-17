package com.pragma.powerup.domain;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.spi.feignclient.IEmployeeFeignPort;
import com.pragma.powerup.domain.spi.feignclient.IRestaurantFeignPort;
import com.pragma.powerup.domain.spi.passwordencoder.IUserPasswordEncoderPort;
import com.pragma.powerup.domain.spi.token.IToken;
import com.pragma.powerup.domain.usecase.UserUseCase;
import com.pragma.powerup.factory.UserTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        UserModel userModel= UserTestData.getUser();

        Mockito.when(token.getBearerToken()).thenReturn("Bearer token");
        Mockito.when(token.getUserAuthenticatedRol("Bearer token")).thenReturn("ADMIN");
        Mockito.when(userPasswordEncoderPort.encode(Mockito.any())).thenReturn("xfgghrtyrdgd");

        userUseCase.saveUser(userModel);

        Mockito.verify(userPasswordEncoderPort).encode("password");
        Mockito.verify(userPersistencePort).saveUser(Mockito.any(UserModel.class));
    }


}
