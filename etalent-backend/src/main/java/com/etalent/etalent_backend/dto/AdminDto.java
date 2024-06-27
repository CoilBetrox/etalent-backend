package com.etalent.etalent_backend.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    //Dto para uso común
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
}
