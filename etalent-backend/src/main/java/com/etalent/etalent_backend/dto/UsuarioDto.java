package com.etalent.etalent_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Integer idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String sapUsuario;
    private String estadoUsuario;
    private RolUsuarioDto rolUsuario;
}