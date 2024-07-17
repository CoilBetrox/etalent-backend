package com.etalent.etalent_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles_admin")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class RolAdmin implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rol")
    @EqualsAndHashCode.Include
    private Integer idRol;

    @Column(name = "nombre_rol")
    @EqualsAndHashCode.Include
    private String nombreRol;

    @ManyToMany(mappedBy = "rolAdmins")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Admin> admins = new HashSet<>();

    @Override
    public String getAuthority() {
        return this.nombreRol;
    }
}
