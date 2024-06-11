package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.RolUsuarioDto;

import java.util.List;

public interface RolUsuarioService {
    RolUsuarioDto createRolUsuario(RolUsuarioDto rolUsuarioDto);
    RolUsuarioDto getRolById(Integer rolId);
    List<RolUsuarioDto> getAllRolUsuarios();
    RolUsuarioDto updateRolUsuario(Integer rolUsuarioId, RolUsuarioDto updatedRolUsuario);
    void deleteRolUsuario(Integer rolId);
}
