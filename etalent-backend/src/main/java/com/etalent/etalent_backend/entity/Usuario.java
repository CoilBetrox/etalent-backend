package com.etalent.etalent_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
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
    private Integer idUsuario;

    //Nombre Apellido
    @Column(name = "nombre_usuario")
    @EqualsAndHashCode.Include
    private String nombreUsuario;

    //20011XXX
    @Column(name = "sap_usuario")
    @EqualsAndHashCode.Include
    private String sapUsuario;

    //nombreapellido@correo.com
    @Column(name= "correo_usuario")
    @EqualsAndHashCode.Include
    private String correoUsuario;

    //cajero, empl mostrador, ...
    @Column(name= "cargo_usuario")
    @EqualsAndHashCode.Include
    private String cargoUsuario;

    //Tecnología, Mujer, Hombre
    @Column(name= "zona_usuario")
    @EqualsAndHashCode.Include
    private String zonaUsuario;

    @Column(name= "empresa_usuario")
    @EqualsAndHashCode.Include
    private String empresaUsuario;

    @Column(name= "provincia_usuario")
    @EqualsAndHashCode.Include
    private String provinciaUsuario;

    @Column(name= "tienda_usuario")
    @EqualsAndHashCode.Include
    private String tiendaUsuario;

    @Column(name= "jornada_usuario")
    @EqualsAndHashCode.Include
    private String jornadaUsuario;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ComentarioFeedback> comentarios = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "usuarios")
    private Set<CursoUsuario> cursosUsuario = new HashSet<>();

}