package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    @NotBlank(message = "Apellido es requerido")
    private String apellido;
    @NotBlank(message = "Documento es requerido")
    @Pattern(regexp = "\\d+", message = "El documento identidad debe ser númerico")
    private String documentoIdentidad;
    @NotBlank(message = "Celular es requerido")
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "El número de celular debe contener máximo 13 caracteres y puede contener el símbolo '+' al inicio")
    private String celular;

    private LocalDate fechaNacimiento;
    @NotBlank(message = "Correo es requerido")
    @Email(message = "Correo no valido")
    private String correo;
    @NotBlank(message = "clave es requerido")
    private String clave;
    private Long rol;
}
