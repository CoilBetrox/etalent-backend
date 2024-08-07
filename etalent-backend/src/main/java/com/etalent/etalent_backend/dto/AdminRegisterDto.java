package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRegisterDto {

    private Integer idAdmin;
    private String nombreAdmin;
    private String sapAdmin;
    private String correoAdmin;
    private String contraAdmin;
    private String empresaAdmin;
    private String provinciaAdmin;
    private String cargoAdmin;
    private String zonaAdmin;
    private String tiendaAdmin;
    private String estadoAdmin;
    private boolean isVerified;
    private String verificationToken;
    private Set<RolAdminDto> rolAdmins = new HashSet<>();
}