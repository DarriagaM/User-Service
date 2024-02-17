package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OwnerModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.OwnerEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    @Mapping(target = "rol.id",source = "rol.id")
    UserEntity toEntity(UserModel userModel);
    @Mapping(target = "rol.id",source = "rol.id")
    UserModel toUserModel(UserEntity userEntity);
    @Mapping(target = "rol.id",source = "rol.id")
    OwnerEntity toOwnerEntity(OwnerModel ownerModel);
    @Mapping(target = "rol.id",source = "rol.id")
    OwnerModel toOwnerModel(OwnerEntity ownerEntity);
    List<UserModel> toUserModelList(List<UserEntity> userEntityList);

    List<OwnerEntity> toOwnerEntityList(List<OwnerEntity> ownerEntities);
}
