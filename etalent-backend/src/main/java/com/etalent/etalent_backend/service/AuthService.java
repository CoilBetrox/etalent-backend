package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.AdminLoginDto;

public interface AuthService {
    String login(AdminLoginDto adminLoginDto);
}
