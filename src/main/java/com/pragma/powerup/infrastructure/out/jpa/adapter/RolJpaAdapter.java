package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.spi.IRolPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRolRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolPersistencePort {
    private final IRolEntityMapper rolEntityMapper;
    private final IRolRepository rolRepository;

    @Override
    public RolModel saveRol(RolModel rolModel) {
        RolEntity rolEntity = rolRepository.save(rolEntityMapper.toEntity(rolModel));
        return rolEntityMapper.toRolModel(rolEntity);
    }

    @Override
    public List<RolModel> getAllRol() {
        List<RolEntity> rolEntityList = rolRepository.findAll();
        if(rolEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return rolEntityMapper.toRolModelList(rolEntityList);
    }

    @Override
    public RolModel getRolById(Long id) {
        Optional<RolEntity> optionalRolEntity = rolRepository.findById(id);
        RolEntity rolEntity = optionalRolEntity.orElse(null);
        return rolEntityMapper.toRolModel(rolEntity);
    }

    @Override
    public void deleteRolById(Long id) {
        rolRepository.deleteById(id);
    }
}
