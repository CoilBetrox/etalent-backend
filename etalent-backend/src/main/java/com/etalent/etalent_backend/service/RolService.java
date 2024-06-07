package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.RolDto;

import java.util.List;

public interface RolService {

    RolDto createRol(RolDto rolDto);
    RolDto getRolById(Integer id);
    List<RolDto> getAllRols();
    RolDto updateRol(Integer rodId ,RolDto updatedRol);
    void deleteRol(Integer rolId);
}
