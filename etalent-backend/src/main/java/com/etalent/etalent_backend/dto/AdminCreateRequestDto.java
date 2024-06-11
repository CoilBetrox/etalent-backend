package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateRequestDto {
    private String nombreAdmin;
    private String sapAdmin;
    private String correoAdmin;
    private Byte[] imagenAdmin;
    private String empresaAdmin;
    private String provinciaAdmin;
    private String zonaAdmin;
    private String estadoAdmin;
    private Set<Integer> rolIds;
}
