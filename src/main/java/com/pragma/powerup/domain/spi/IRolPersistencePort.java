package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.RolModel;

import java.util.List;

public interface IRolPersistencePort {
    RolModel saveRol(RolModel rolModel);
    List<RolModel> getAllRol();
    RolModel getRolById(Long id);
    void deleteRolById(Long id);
}
