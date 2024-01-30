package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RolRequestDto {
    //@NotBlank(message = "Escribire nombre rol")
    private String nombre;
    //@NotBlank(message = "Proporcionar una descripcion")
    private String descripcion;
}
