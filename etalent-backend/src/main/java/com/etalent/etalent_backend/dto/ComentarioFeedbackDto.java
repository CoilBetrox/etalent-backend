package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioFeedbackDto {

    private Integer idComentario;
    private Integer feedbackId;
    private Integer usuarioId;
    private Integer adminId;
    private String contenido;
    private Date fechaComentario;
}
