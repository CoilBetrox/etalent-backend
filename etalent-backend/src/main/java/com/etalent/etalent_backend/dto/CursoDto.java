package com.etalent.etalent_backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDto {

    private Integer idCurso;
    private String nombreCurso;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
}
