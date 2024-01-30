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
public class RolHandler implements IRolHandler{
    private final IRolServicePort iRolServicePort;
    private final IRolRequestMapper iRolRequestMapper;
    private final IRolResponseMapper iRolResponseMapper;

    @Override
    public void saveRol(RolRequestDto rolRequestDto) {
        RolModel rol = iRolRequestMapper.toRolModel(rolRequestDto);
        iRolServicePort.saveRol(rol);
    }

    @Override
    public RolResponseDto getRolById(Long id) {
        RolModel rol = iRolServicePort.getRolById(id);
        return iRolResponseMapper.toRolResponse(rol);
    }

    @Override
    public List<RolResponseDto> getAllRol() {
        List<RolModel> rolModelList = iRolServicePort.getAllRol();
        return iRolResponseMapper.toRolResponseList(rolModelList);
    }

    @Override
    public void deleteRolById(Long id) {
        iRolServicePort.deleteRolById(id);
    }
}
