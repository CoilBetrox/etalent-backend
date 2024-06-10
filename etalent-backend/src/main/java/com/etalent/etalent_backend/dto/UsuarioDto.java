package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Integer idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String sapUsuario;
    private String estadoUsuario;
    private String rolUsuario;
}
