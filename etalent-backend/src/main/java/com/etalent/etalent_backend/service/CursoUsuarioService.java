package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.*;

import java.util.List;

public interface CursoUsuarioService {
    CursoDto createCursoUsuario(CursoDto cursoDto);
    CursoUsuarioRelacionDto assignUserToCurso(Integer idCursoUsuario, Integer idUsuario);
    List<CursoUsuarioDto> getCursosByIdUsuario(Integer idUsuario);
    CursoUsuarioDto updateCursoUsuario(Integer idCursoUsuario, Integer avanceCurso, String estadoCurso);
    List<CursoDto> getAllCursos();
    List<CursoDto> getAllCursosSimple();
    CursoConUsuariosDto getUsuarioByCursoId(Integer idCurso);
    void quitarUsuarioDeCurso(Integer idCursoUsuario, Integer idUsuario);
}
