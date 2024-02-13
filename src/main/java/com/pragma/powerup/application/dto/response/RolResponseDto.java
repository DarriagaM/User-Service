package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RolResponseDto {
    private Long id;
    private String nombre;
    private String descripcion;
}
