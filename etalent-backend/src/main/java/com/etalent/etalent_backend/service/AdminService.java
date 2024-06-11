package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.AdminCreateRequestDto;
import com.etalent.etalent_backend.dto.AdminDto;

import java.util.List;

public interface AdminService {

    AdminDto createAdmin(AdminDto adminDto);
    AdminDto getAdminById(Integer id);
    List<AdminDto> getAllAdmins();
    AdminDto updateAdmin(Integer adminId, AdminDto updatedAdmin);
    void deleteAdmin(Integer adminId);

    AdminDto addRolToAdmin(Integer adminId, Integer rolId);
    AdminDto createAdminWithRoles(AdminCreateRequestDto adminCreateRequestDto);
}
