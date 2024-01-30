package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.RolModel;

import java.util.List;

public interface IRolServicePort {
    void saveRol(RolModel rol);
    RolModel getRolById(Long id);
    List<RolModel> getAllRol();
    void deleteRolById(Long id);
}
