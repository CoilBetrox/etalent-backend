package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.*;
import com.etalent.etalent_backend.entity.CursoUsuario;
import com.etalent.etalent_backend.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface CursoUsuarioMapperM {
    CursoUsuarioMapperM INSTANCE = Mappers.getMapper(CursoUsuarioMapperM.class);

    CursoUsuarioDto toCursoUsuarioDto(CursoUsuario cursoUsuario);

    @InheritInverseConfiguration
    CursoUsuario toCursoUsuario(CursoUsuarioDto cursoUsuarioDto);

    @Mapping(source = "cursoUsuario.usuarios", target = "usuarios")
    CursoUsuarioRelacionDto toCursoUsuarioRelacionDto(CursoUsuario cursoUsuario);

    @Mapping(target = "idUsuario", source = "idUsuario")
    @Mapping(target = "nombreUsuario", source = "nombreUsuario")
    UsuarioSimpleDto toUsuarioSimpleDto(Usuario usuario);

    default List<UsuarioSimpleDto> mapUsuarios(Set<Usuario> usuarios) {
        if (usuarios == null) {
            return Collections.emptyList();
        }
        return usuarios.stream()
                .map(this::toUsuarioSimpleDto)
                .collect(Collectors.toList());
    }

    CursoUsuarioSimpleDto toCursoUsuarioSimpleDto(CursoUsuario cursoUsuario);

    List<CursoUsuarioDto> toCursoUsuarioDtoList(List<CursoUsuario> cursoUsuarios);
    List<CursoUsuarioSimpleDto> toCursoUsuarioSimpleDtoList(List<CursoUsuario> cursoUsuarios);

    @Mapping(target = "usuarios", source = "usuarios")
    CursoConUsuariosDto toCursoConUsuariosDto(CursoUsuario cursoUsuario);
}
