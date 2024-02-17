package com.pragma.powerup.application.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmployeeRequestDto {

    @NotBlank()
    private String idRestaurant;
    @NotBlank()
    private String idEmployee;
}
