package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateDto {
    private String provinciaAdmin;
    private String empresaAdmin;
    private String zonaAdmin;
}