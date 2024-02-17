package com.pragma.powerup.domain.spi.feignclient;

import com.pragma.powerup.domain.model.RestaurantModel;

public interface IRestaurantFeignPort {
    RestaurantModel getRestaurantByIdOwner(Long idOwner);
}
