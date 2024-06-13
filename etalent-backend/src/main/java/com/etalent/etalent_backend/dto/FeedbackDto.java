package com.etalent.etalent_backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {
    private Integer idFeedback;
    private String tituloFeedback;
    private String descripcionFeedback;
    private String comentarioFeedback;
    private Date fechaCreacionFeedback;
    private Integer idUsuario;
    private Integer idAdmin;
}
