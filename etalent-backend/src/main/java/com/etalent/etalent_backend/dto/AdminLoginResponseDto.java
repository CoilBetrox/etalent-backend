package com.etalent.etalent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginResponseDto {
    private String accessToken;
    private Set<String> rolAdmins;
}