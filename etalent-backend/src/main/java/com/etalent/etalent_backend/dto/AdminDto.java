package com.etalent.etalent_backend.dto;

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
public class AdminDto {
    private Integer idAdmin;
    private String nombreAdmin;
    private String sapAdmin;
    private String correoAdmin;
    private Byte[] imagenAdmin;
    private String empresaAdmin;
    private String provinciaAdmin;
    private String zonaAdmin;
    private String estadoAdmin;
    private Set<RolAdminDto> rolAdmins = new HashSet<>();
    //add Feedback
}
