package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.CursoConUsuariosDto;
import com.etalent.etalent_backend.dto.CursoUsuarioDto;
import com.etalent.etalent_backend.dto.CursoUsuarioSimpleDto;

import java.util.List;

public interface CursoUsuarioService {
    CursoUsuarioDto createCursoUsuario(CursoUsuarioDto cursoUsuarioDto);
    CursoUsuarioDto assignUserToCurso(Integer idCursoUsuario, Integer idUsuario);
    List<CursoUsuarioDto> getCursosByIdUsuario(Integer idUsuario);
    CursoUsuarioDto updateCursoUsuario(Integer idCursoUsuario, Integer avanceCurso, String estadoCurso);
    List<CursoUsuarioDto> getAllCursos();
    List<CursoUsuarioSimpleDto> getAllCursosSimple();
    CursoConUsuariosDto getUsuariosByCursoId(Integer idCursoUsuario);
}
