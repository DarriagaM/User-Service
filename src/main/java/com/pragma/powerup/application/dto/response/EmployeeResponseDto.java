package com.pragma.powerup.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeResponseDto {

    private Long id;

    private String idRestaurant;

    private String idEmployee;
}
