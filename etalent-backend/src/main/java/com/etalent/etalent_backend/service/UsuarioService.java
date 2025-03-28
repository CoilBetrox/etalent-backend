package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.UsuarioDirectorioDto;
import com.etalent.etalent_backend.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    UsuarioDto createUsuario(UsuarioDto usuarioDto);
    UsuarioDto getUsuarioById(Integer usuarioId);
    List<UsuarioDirectorioDto> getAllUsuarios();
    UsuarioDto updateUsuario(Integer usuarioId, UsuarioDto updatedUsuario);
    void deleteUsuario(Integer usuarioId);

    List<UsuarioDirectorioDto> getUsuarioBySap(String sapUsuario);
    List<UsuarioDto> createUsuariosBulk(List<UsuarioDto> usuariosDto);
}