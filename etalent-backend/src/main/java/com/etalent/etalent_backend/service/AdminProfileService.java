package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.*;
import com.etalent.etalent_backend.entity.Admin;

import java.util.List;

public interface AdminProfileService {
    void updateAdminPartial(AdminUpdateDto adminUpdateDto, String email);
    AdminPerfilDto getAdminByEmail(String email);
    List<AdminDto> getAdminsByRole(String rolNombre);
    List<UsuarioDto> getUsuariosByAdmin(Integer adminId);
    void reassignUsers(Integer oldAdminId, Integer newAdminId);
    AdminPerfilDto getAdminBySap(String sap);
    AdminPerfilDto updateAdminEstado(Integer adminId, AdminPerfilDto adminUpdatedto);

    List<AdminRegisterDto> createJefesBulk(List<AdminRegisterDto> adminsDtos);
}