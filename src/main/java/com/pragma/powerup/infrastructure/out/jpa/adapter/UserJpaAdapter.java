package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.OwnerModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.OwnerEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;
    @Override
    public UserModel saveUser(UserModel user) {

        if(user instanceof OwnerModel){
            OwnerEntity ownerEntity = userRepository.save(userEntityMapper.toOwnerEntity((OwnerModel)user));
            return userEntityMapper.toOwnerModel(ownerEntity);
        }

        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel getUserById(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        UserEntity userEntity = optionalUserEntity.orElse(null);
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public boolean existsUserById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public List<UserModel> getAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if(userEntityList.isEmpty()){
            throw  new NoDataFoundException();
        }
        return userEntityMapper.toUserModelList(userEntityList);
    }


    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel getUserByEmail(String email) {
        Optional<UserEntity> optionalUserEntity = userRepository.findOneByEmail(email);
        UserEntity userEntity = optionalUserEntity.orElse(null);
        return userEntityMapper.toUserModel(userEntity);
    }
}
