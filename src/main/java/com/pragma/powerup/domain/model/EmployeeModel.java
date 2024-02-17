package com.pragma.powerup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    private Long id;

    private String idRestaurant;

    private String idEmployee;
}
