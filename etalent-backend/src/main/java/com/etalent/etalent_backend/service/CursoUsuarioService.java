package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.CursoUsuarioDto;

import java.util.List;

public interface CursoUsuarioService {
    CursoUsuarioDto createCursoUsuario(CursoUsuarioDto cursoUsuarioDto, Integer idUsuario);
    List<CursoUsuarioDto> getCursosByIdUsuario(Integer idUsuario);
    CursoUsuarioDto updateCursoUsuario(Integer idCursoUsuario, Integer avanceCurso, String estadoCurso);
}
