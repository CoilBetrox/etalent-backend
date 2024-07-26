package com.etalent.etalent_backend.dto;

import com.etalent.etalent_backend.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoUsuarioDto {

    private Integer idCursoUsuario;
    private String nombreCursoUsuario;
    private Date fechaInicio;
    private Integer avanceCurso;
    private String estadoCurso;
    private Integer idUsuario;
    private String nombreUsuario;
}
