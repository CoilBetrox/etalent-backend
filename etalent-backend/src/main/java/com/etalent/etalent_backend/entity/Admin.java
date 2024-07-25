package com.etalent.etalent_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Admin implements UserDetails{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    @Column(name = "id_admin")
    private Integer idAdmin;

    //Nombre Completo
    @Column(name = "nombre_admin")
    private String nombreAdmin;

    //20011XXX
    @Column(name = "sap_admin")
    private String sapAdmin;

    //Correo@correo.com
    @Column(name = "correo_admin")
    private String correoAdmin;

    //*********
    @Column(name = "contra_admin")
    private String contraAdmin;

    //ETA - RM
    @Column(name = "empresa_admin")
    private String empresaAdmin;

    //Provincias EC
    @Column(name = "provincia_admin")
    private String provinciaAdmin;

    @Column(name = "cargo_admin")
    private String cargoAdmin = "Jefe de Ventas";

    //Zona-seccion (Jefaturas-Belleza-calzado-tecnología)
    @Column(name = "zona_admin")
    private String zonaAdmin = "Jefaturas";

    //Ubicacion tienda (Pana Norte)
    @Column(name = "tienda_admin")
    private String tiendaAdmin;

    //Activo - Inactivo
    @Column(name = "estado_admin")
    private String estadoAdmin;

    @Column(name = "is_verified")
    private boolean isVerified = true;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "admin_rol_admin",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_admin_id")
    )
    private Set<RolAdmin> rolAdmins = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Feedback> feedbacks = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "admin", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Usuario> usuarios = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ComentarioFeedback> comentarios = new HashSet<>();

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolAdmins.stream()
                .map(
                        role -> new SimpleGrantedAuthority(role.getNombreRol())
                )
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.contraAdmin;
    }

    @Override
    public String getUsername() {
        return this.correoAdmin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isVerified;
    }
}