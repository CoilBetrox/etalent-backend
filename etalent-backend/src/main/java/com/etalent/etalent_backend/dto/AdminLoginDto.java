package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginDto {
    private String correoAdmin;
    private String contraAdmin;
    private String rolAdmins;
}