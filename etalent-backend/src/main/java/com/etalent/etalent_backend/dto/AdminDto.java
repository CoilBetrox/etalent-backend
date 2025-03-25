package com.etalent.etalent_backend.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private Integer idAdmin;
    private String nombreAdmin;
    private String sapAdmin;
    private String correoAdmin;
    private String cargoAdmin;
    private String zonaAdmin;
    private String empresaAdmin;
    private String provinciaAdmin;
    private String tiendaAdmin;
    private String estadoAdmin;
}