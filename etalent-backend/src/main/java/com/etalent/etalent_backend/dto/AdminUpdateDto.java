package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateDto {
    private Integer idAdmin;
    private String nombreAdmin;
    private String sapAdmin;
    private String correoAdmin;
}