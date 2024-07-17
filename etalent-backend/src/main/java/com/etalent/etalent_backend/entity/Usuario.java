package com.etalent.etalent_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario")
    @EqualsAndHashCode.Include
    private Integer idUsuario;

    @Column(name = "nombre_usuario")
    @EqualsAndHashCode.Include
    private String nombreUsuario;

    @Column(name= "correo_usuario")
    @EqualsAndHashCode.Include
    private String correoUsuario;

    @Column(name = "sap_usuario")
    @EqualsAndHashCode.Include
    private String sapUsuario;

    @Column(name = "estado_usuario")
    @EqualsAndHashCode.Include
    private String estadoUsuario;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "id_rol_usuario", referencedColumnName = "id_rol_usuario", nullable = false)
    private RolUsuario rolUsuario;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Feedback> feedbacks = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ComentarioFeedback> comentarios = new HashSet<>();
}