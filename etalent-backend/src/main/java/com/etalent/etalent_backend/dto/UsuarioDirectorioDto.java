package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDirectorioDto {
    private Integer idUsuario;
    private String nombreUsuario;
    private String sapUsuario;
    private String correoUsuario;
    private String cargoUsuario;
    private String zonaUsuario;
    private String empresaUsuario;
    private String tiendaUsuario;
    private String jornadaUsuario;
    private RolUsuarioDto rolUsuario;
}
