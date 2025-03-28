package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoConUsuariosDto {
    private Integer idCurso;
    private String nombreCurso;
    private List<UsuarioSimpleDto> usuarios;
}
