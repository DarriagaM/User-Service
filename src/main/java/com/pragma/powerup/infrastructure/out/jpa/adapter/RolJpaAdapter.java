package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.spi.IRolPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolPersistencePort {

    private final IRolEntityMapper iRolEntityMapper;
    private final IRolRepository iRolRepository;
    @Override
    public RolModel saveRol(RolModel rol) {
        RolEntity rolEntity = iRolRepository.save(iRolEntityMapper.toRolEntity(rol));
        return iRolEntityMapper.toRolModel(rolEntity);
    }

    @Override
    public RolModel getRolById(Long id) {
        Optional<RolEntity> optionalRolEntity = iRolRepository.findById(id);
        RolEntity rolEntity = optionalRolEntity.orElse(null);
        return iRolEntityMapper.toRolModel(rolEntity);
    }

    @Override
    public List<RolModel> getAllRol() {
        List<RolEntity> rolEntityList = iRolRepository.findAll();
        if(rolEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return iRolEntityMapper.toRolModelList(rolEntityList);
    }

    @Override
    public void deleteRolById(Long id) {
        iRolRepository.deleteById(id);
    }
}
