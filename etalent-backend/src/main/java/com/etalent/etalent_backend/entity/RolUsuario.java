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
@Table(name = "Roles_Usuario")
public class RolUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rol_usuario")
    private Integer idRolUsuario;

    @Column(name = "nombre_rol_usuario")
    private String nombreRolUsuario;

    @OneToOne(mappedBy = "rolUsuario")
    private Usuario usuario;

}
