package com.pragma.powerup.application.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class RolRequestDto {
    private String nombre;
    private String descripcion;
}
