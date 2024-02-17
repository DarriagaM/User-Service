package com.pragma.powerup.infrastructure.out.feignclient.adapter;

import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.application.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.feignclient.IRestaurantFeignPort;
import com.pragma.powerup.infrastructure.out.feignclient.IRestaurantFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantFeignAdapter implements IRestaurantFeignPort {
    private final IRestaurantFeignClient restaurantFeignClient;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    @Override
    public RestaurantModel getRestaurantByIdOwner(Long idOwner) {
        RestaurantResponseDto restaurantResponseDto =  restaurantFeignClient.getRestaurantByIdOwner(idOwner);
        return restaurantResponseMapper.toRestaurantModel(restaurantResponseDto);
    }
}
