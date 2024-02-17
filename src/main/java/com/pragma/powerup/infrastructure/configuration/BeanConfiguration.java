package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.application.mapper.IEmployeeRequestMapper;
import com.pragma.powerup.application.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.spi.IRolPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.spi.feignclient.IEmployeeFeignPort;
import com.pragma.powerup.domain.spi.feignclient.IRestaurantFeignPort;
import com.pragma.powerup.domain.spi.passwordencoder.IUserPasswordEncoderPort;
import com.pragma.powerup.domain.spi.token.IToken;
import com.pragma.powerup.domain.usecase.RolUseCase;
import com.pragma.powerup.domain.usecase.UserUseCase;
import com.pragma.powerup.infrastructure.out.feignclient.IEmployeeFeignClient;
import com.pragma.powerup.infrastructure.out.feignclient.IRestaurantFeignClient;
import com.pragma.powerup.infrastructure.out.feignclient.adapter.EmployeeFeignAdapter;
import com.pragma.powerup.infrastructure.out.feignclient.adapter.RestaurantFeignAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.RolJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.passwordencoder.BCryptPasswordEncoderAdapter;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRolRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.out.token.TokenAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRolEntityMapper rolEntityMapper;
    private final IRolRepository rolRepository;

    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;

    private final IRestaurantFeignClient restaurantFeignClient;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    private final IEmployeeFeignClient employeeFeignClient;
    private final IEmployeeRequestMapper employeeRequestMapper;

    @Bean
    public IUserPasswordEncoderPort userPasswordEncoderPort(){
        return new BCryptPasswordEncoderAdapter();
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userEntityMapper,userRepository);
    }

    @Bean
    public IToken token(){
        return new TokenAdapter();
    }

    @Bean
    public IRestaurantFeignPort restaurantFeignPort(){
        return new RestaurantFeignAdapter(restaurantFeignClient,restaurantResponseMapper);
    }

    @Bean
    public IEmployeeFeignPort employeeFeignPort(){
        return new EmployeeFeignAdapter(employeeFeignClient,employeeRequestMapper);
    }


    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(),userPasswordEncoderPort(),token(), restaurantFeignPort(),employeeFeignPort());
    }

    @Bean
    public IRolPersistencePort rolPersistencePort() {
        return new RolJpaAdapter(rolEntityMapper, rolRepository);
    }

    @Bean
    public IRolServicePort rolServicePort() {
        return new RolUseCase(rolPersistencePort());
    }
}