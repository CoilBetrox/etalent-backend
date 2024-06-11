package com.etalent.etalent_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_feedback")
    private Integer idFeedback;

    @Column(name = "titulo_feedback")
    private String tituloFeedback;

    @Column(name = "descripcion_feedback")
    private String descripcionFeedback;

    @Column(name = "comentario_feedback")
    private String comentarioFeedback;

    @Column(name = "fecha_creacion_feedback")
    private Date fechaCreacionFeedback;
}
