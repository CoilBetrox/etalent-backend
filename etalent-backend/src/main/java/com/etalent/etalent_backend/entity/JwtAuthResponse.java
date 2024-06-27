package com.etalent.etalent_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse{
    private String accessToken;
    private String tokenType = "Bearer";
}
