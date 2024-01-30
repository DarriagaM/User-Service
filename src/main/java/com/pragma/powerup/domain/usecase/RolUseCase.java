package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.spi.IRolPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

import java.util.List;

public class RolUseCase implements IRolServicePort {

    private final IRolPersistencePort rolPersistencePort;

    public RolUseCase(IRolPersistencePort rolPersistencePort) {
        this.rolPersistencePort = rolPersistencePort;
    }

    @Override
    public void saveRol(RolModel rol) {
        rolPersistencePort.saveRol(rol);
    }

    @Override
    public RolModel getRolById(Long id) {
        return rolPersistencePort.getRolById(id);
    }

    @Override
    public List<RolModel> getAllRol() {
        return rolPersistencePort.getAllRol();
    }

    @Override
    public void deleteRolById(Long id) {
        rolPersistencePort.deleteRolById(id);
    }
}
