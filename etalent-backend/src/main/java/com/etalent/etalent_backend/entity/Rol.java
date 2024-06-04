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
@Table(name = "Roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "nombre_rol")
    private String nombreRol;

    /*
    @OneToOne
    private Admin admin;
     */

}
