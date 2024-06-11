package com.etalent.etalent_backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolUsuarioDto {

    private Integer idRolUsuario;
    private String nombreRolUsuario;
}
