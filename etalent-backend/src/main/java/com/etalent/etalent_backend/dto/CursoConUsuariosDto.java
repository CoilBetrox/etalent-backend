package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoConUsuariosDto {
    private Integer idCursoUsuario;
    private String nombreCursoUsuario;
    private List<UsuarioSimpleDto> usuarios;
}
