package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSimpleDto {
    private Integer idUsuario;
    private String nombreUsuario;
    private String sapUsuario;
}
