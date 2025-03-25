package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.*;
import com.etalent.etalent_backend.entity.CursoUsuario;
import com.etalent.etalent_backend.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CursoUsuarioMapperM {
    CursoUsuarioMapperM INSTANCE = Mappers.getMapper(CursoUsuarioMapperM.class);

    CursoUsuarioDto toCursoUsuarioDto(CursoUsuario cursoUsuario);

    @InheritInverseConfiguration
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "curso", ignore = true)
    CursoUsuario toCursoUsuario(CursoUsuarioDto cursoUsuarioDto);

    @Mapping(target = "usuario", expression = "java(cursoUsuario.getUsuario() != null ? toUsuarioSimpleDto(cursoUsuario.getUsuario()) : null)")
    CursoUsuarioRelacionDto toCursoUsuarioRelacionDto(CursoUsuario cursoUsuario);

    @Mapping(target = "idUsuario", source = "idUsuario")
    @Mapping(target = "nombreUsuario", source = "nombreUsuario")
    UsuarioSimpleDto toUsuarioSimpleDto(Usuario usuario);

    CursoUsuarioSimpleDto toCursoUsuarioSimpleDto(CursoUsuario cursoUsuario);

    List<CursoUsuarioDto> toCursoUsuarioDtoList(List<CursoUsuario> cursoUsuarios);
    List<CursoUsuarioSimpleDto> toCursoUsuarioSimpleDtoList(List<CursoUsuario> cursoUsuarios);

    @Mapping(target = "idCurso", source = "curso.idCurso")
    @Mapping(target = "nombreCurso", source = "curso.nombreCurso")
    @Mapping(target = "usuarios", expression = "java(cursoUsuario.getUsuario() != null ? List.of(toUsuarioSimpleDto(cursoUsuario.getUsuario())) : new ArrayList<>())")
    CursoConUsuariosDto toCursoConUsuariosDto(CursoUsuario cursoUsuario);
}
