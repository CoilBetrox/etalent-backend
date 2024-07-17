package com.etalent.etalent_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles_usuario")
public class RolUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rol_usuario")
    private Integer idRolUsuario;

    @Column(name = "nombre_rol_usuario")
    private String nombreRolUsuario;

    @OneToMany(mappedBy = "rolUsuario")
    private List<Usuario> usuario;
}