package com.etalent.etalent_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name= "correo_usuario")
    private String correoUsuario;

    @Column(name = "sap_usuario")
    private String sapUsuario;

    @Column(name = "estado_usuario")
    private String estadoUsuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol_usuario", referencedColumnName = "id_rol_usuario")
    private RolUsuario rolUsuario;
}
