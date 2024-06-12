package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.RolUsuarioDto;
import com.etalent.etalent_backend.dto.UsuarioDto;

import java.util.List;

public interface RolUsuarioService {
    RolUsuarioDto createRolUsuario(RolUsuarioDto rolUsuarioDto);
    RolUsuarioDto getRolById(Integer rolId);
    List<RolUsuarioDto> getAllRolUsuarios();
    RolUsuarioDto updateRolUsuario(Integer rolUsuarioId, RolUsuarioDto updatedRolUsuario);
    void deleteRolUsuario(Integer rolId);
    UsuarioDto createUsuarioWithRol(UsuarioDto usuarioDto);
    UsuarioDto updateRolUsuario(Integer UsuarioId, Integer rolUsuarioId);
}
