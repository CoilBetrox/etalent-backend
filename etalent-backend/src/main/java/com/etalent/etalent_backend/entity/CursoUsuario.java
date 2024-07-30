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

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "avance_curso")
    private Integer avanceCurso;

    @Column(name = "estado_curso")
    private String estadoCurso;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_curso_usuario",
            joinColumns = @JoinColumn(name = "curso_usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios = new HashSet<>();
}
