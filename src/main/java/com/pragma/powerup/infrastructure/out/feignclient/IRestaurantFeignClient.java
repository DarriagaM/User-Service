package com.pragma.powerup.infrastructure.out.feignclient;

import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "restaurant", name = "plazoleta-Service", url = "localhost:8092/restaurant")
public interface IRestaurantFeignClient {
    @GetMapping("/restaurantByIdOwner/{idOwner}")
    RestaurantResponseDto getRestaurantByIdOwner(@PathVariable(value = "idOwner") Long idOwner);
}
