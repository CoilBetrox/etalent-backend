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
public class Admin implements UserDetails{

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

    @Column(name = "contra_admin")
    private String contraAdmin;

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

    @Column(name = "is_verified")
    private boolean isVerified = true;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "admin_rol_admin",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_admin_id")
    )
    private Set<RolAdmin> rolAdmins = new HashSet<>();

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Feedback> feedbacks = new HashSet<>();

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuarios = new HashSet<>();

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

    //Implements User Details

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
