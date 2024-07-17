package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.AdminDto;
import com.etalent.etalent_backend.dto.AdminPerfilDto;
import com.etalent.etalent_backend.dto.AdminUpdateDto;
import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.entity.Admin;

import java.util.List;

public interface AdminProfileService {
    void updateAdminPartial(AdminUpdateDto adminUpdateDto, String email);
    AdminPerfilDto getAdminByEmail(String email);
    List<AdminDto> getAdminsByRole(String rolNombre);
    List<UsuarioDto> getUsuariosByAdmin(Integer adminId);
}