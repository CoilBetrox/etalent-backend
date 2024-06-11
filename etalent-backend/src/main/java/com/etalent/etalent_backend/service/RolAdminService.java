package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.RolAdminDto;

import java.util.List;

public interface RolAdminService {

    RolAdminDto createRol(RolAdminDto rolAdminDto);
    RolAdminDto getRolById(Integer id);
    List<RolAdminDto> getAllRols();
    RolAdminDto updateRol(Integer rodId , RolAdminDto updatedRol);
    void deleteRol(Integer rolId);
}
