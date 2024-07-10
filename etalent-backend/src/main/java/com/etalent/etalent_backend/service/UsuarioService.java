package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    UsuarioDto createUsuario(UsuarioDto usuarioDto);
    UsuarioDto getUsuarioById(Integer usuarioId);
    List<UsuarioDto> getAllUsuarios();
    UsuarioDto updateUsuario(Integer usuarioId, UsuarioDto updatedUsuario);
    void deleteUsuario(Integer usuarioId);

}