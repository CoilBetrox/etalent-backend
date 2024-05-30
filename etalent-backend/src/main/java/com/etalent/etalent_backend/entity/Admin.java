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
@Table(name = "Admins")
public class Admin {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    @Column(name = "id_admin")
    private Integer idAdmin;

    @Column(name = "nombre_admin")
    private String nombreAdmin;

    @Column(name = "sap_admin")
    private String sapAdmin;

    @Column(name = "correo_admin")
    private String correoAdmin;

    @Column(name = "imagen_admin")
    private String imagenAdmin;

    @Column(name = "empresa_admin")
    private String empresaAdmin;

    @Column(name = "provincia_admin")
    private String provinciaAdmin;

    @Column(name = "zona_admin")
    private String zonaAdmin;

    @Column(name = "estado_admin")
    private String estadoAdmin;

    @Column(name = "rol_admin")
    private String rolAdmin;

    /*
    @OneToOne
    private Role role;
    */
}
