package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminPerfilDto {
    private Integer idAdmin;
    private String nombreAdmin;
    private String sapAdmin;
    private String correoAdmin;
    private String empresaAdmin;
    private String provinciaAdmin;
    private String zonaAdmin;
}
