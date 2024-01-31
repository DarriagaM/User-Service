package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.handler.IRolHandler;
import com.pragma.powerup.application.mapper.IRolRequestMapper;
import com.pragma.powerup.application.mapper.IRolResponseMapper;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.model.RolModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class RolHandler implements IRolHandler {

    private final IRolServicePort rolServicePort;
    private final IRolRequestMapper rolRequestMapper;
    private final IRolResponseMapper rolResponseMapper;

    @Override
    public void saveRol(RolRequestDto rolRequestDto) {
        RolModel rolModel = rolRequestMapper.toRolModel(rolRequestDto);
        rolServicePort.saveRol(rolModel);
    }

    @Override
    public RolResponseDto getRolById(Long id) {
        RolModel rolModel = rolServicePort.getRolById(id);
        return rolResponseMapper.toRolResponseDto(rolModel);
    }

    @Override
    public List<RolResponseDto> getAllRol() {
        List<RolModel> rolModelList = rolServicePort.getAllRol();
        return rolResponseMapper.toRolResponseDtoList(rolModelList);
    }

    @Override
    public void deleteRolById(Long id) {
        rolServicePort.deleteRolById(id);
    }
}
