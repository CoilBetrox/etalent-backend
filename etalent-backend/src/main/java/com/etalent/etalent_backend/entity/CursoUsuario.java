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
@Table(name = "cursos_usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class CursoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_curso_usuario")
    private Integer idCursoUsuario;

    @Column(name = "nombre_curso_usuario")
    @EqualsAndHashCode.Include
    private String nombreCursoUsuario;

    @Column(name = "descripcion_curso")
    private String descripcionCurso;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "avance_curso")
    private Integer avanceCurso;

    @Column(name = "estado_curso")
    private String estadoCurso;

    @EqualsAndHashCode.Exclude
    @ToString.Include
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso", nullable = false)
    private Curso curso;
}
