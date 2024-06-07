package com.etalent.etalent_backend.dto;

import com.etalent.etalent_backend.entity.Admin;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolDto {
    private Integer idRol;
    private String nombreRol;
    //private AdminDto adminDto;
}
