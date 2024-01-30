package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort iUserServicePort;
    private final IUserResponseMapper iUserResponseMapper;
    private final IUserRequestMapper iUserRequestMapper;
    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        UserModel user = iUserRequestMapper.toUser(userRequestDto);
        iUserServicePort.saveUser(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        UserModel user = iUserServicePort.getUserById(id);
        return iUserResponseMapper.toUserResponse(user);
    }

    @Override
    public boolean existsUserById(Long id) {
        return iUserServicePort.existsUserById(id);
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        List<UserModel> userModelList = iUserServicePort.getAllUser();
        return iUserResponseMapper.toUserResponseList(userModelList);
    }

    @Override
    public void deleteUserById(Long id) {
        iUserServicePort.deleteUserById(id);
    }
}
