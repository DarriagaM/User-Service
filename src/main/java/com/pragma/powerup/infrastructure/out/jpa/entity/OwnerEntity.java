package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Table(name = "propietarios")
@Data
public class OwnerEntity extends UserEntity{
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
}
