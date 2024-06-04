package com.etalent.etalent_backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String rolAdmin;
}
