package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.AdminRegisterDto;

public interface AdminRegisterService {

    AdminRegisterDto registerAdmin(AdminRegisterDto adminRegisterDto);
    String authenticateAdmin(String correoAdmin, String contraAdmin);
}
