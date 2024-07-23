package com.etalent.etalent_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_feedback")
    private Integer idFeedback;

    @Column(name = "descripcion_feedback")
    private String descripcionFeedback;

    @Column(name = "tipo_feedback")
    private String tipoFeedback;

    @Column(name = "fecha_creacion_feedback")
    private Date fechaCreacionFeedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ComentarioFeedback> comentarios = new HashSet<>();
}