package com.etalent.etalent_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
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

    @Lob
    @Column(name = "imagen_admin")
    private Byte[] imagenAdmin;

    @Column(name = "empresa_admin")
    private String empresaAdmin;

    @Column(name = "provincia_admin")
    private String provinciaAdmin;

    @Column(name = "zona_admin")
    private String zonaAdmin;

    @Column(name = "estado_admin")
    private String estadoAdmin;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "admin_rol_admin",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_admin_id")
    )
    private Set<RolAdmin> rolAdmins = new HashSet<>();

    public Admin(Integer idAdmin){
        this.idAdmin = idAdmin;
    }

    //Bidireccionalidad
    public void addRol(RolAdmin rol) {
        rolAdmins.add(rol);
        rol.getAdmins().add(this);
    }

    public void removeRol(RolAdmin rol){
        rolAdmins.remove(rol);
        rol.getAdmins().remove(this);
    }
}
