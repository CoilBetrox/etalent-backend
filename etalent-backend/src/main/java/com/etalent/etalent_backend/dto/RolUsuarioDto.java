package com.etalent.etalent_backend.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolUsuarioDto {

    private Integer idRolUsuario;
    private String nombreRolUsuario;
}
