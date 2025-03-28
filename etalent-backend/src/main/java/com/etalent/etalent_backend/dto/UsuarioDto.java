package com.etalent.etalent_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Integer idUsuario;
    private String nombreUsuario;
    private String sapUsuario;
    private String correoUsuario;
    private String cargoUsuario;
    private String zonaUsuario;
    private String empresaUsuario;
    private String provinciaUsuario;
    private String tiendaUsuario;
    private String jornadaUsuario;
    private String estadoUsuario;
    private RolUsuarioDto rolUsuario;
}
