package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.RolModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String documentoIdentidad;
    private String celular;
    private LocalDate fechaNacimiento;
    private String email;
    private String clave;
    private RolModel rol;
}
