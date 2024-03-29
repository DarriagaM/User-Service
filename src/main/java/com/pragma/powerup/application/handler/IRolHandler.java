package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.dto.response.RolResponseDto;

import java.util.List;

public interface IRolHandler {
    void saveRol(RolRequestDto rolRequestDto);
    RolResponseDto getRolById(Long id);
    List<RolResponseDto> getAllRol();
    void deleteRolById(Long id);
}
