package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.CursoConUsuariosDto;
import com.etalent.etalent_backend.dto.CursoDto;
import com.etalent.etalent_backend.dto.UsuarioSimpleDto;
import com.etalent.etalent_backend.entity.Curso;
import com.etalent.etalent_backend.entity.CursoUsuario;
import com.etalent.etalent_backend.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface CursoMapperM {
    CursoMapperM INSTANCE = Mappers.getMapper(CursoMapperM.class);

    @Mapping(target = "cursosUsuario", ignore = true)
    Curso toCurso(CursoDto cursoDto);
    CursoDto toCursoDto(Curso curso);
    List<CursoDto> toCursoDtoList(List<Curso> cursos);

    @Mapping(target = "usuarios", source = "cursosUsuario", qualifiedByName = "toUsuarioSimpleDtoList")
    CursoConUsuariosDto toCursoConUsuariosDto(Curso curso);

    @Named("toUsuarioSimpleDtoList")
    default List<UsuarioSimpleDto> toUsuarioSimpleDtoList(List<CursoUsuario> cursoUsuarios) {
        return cursoUsuarios.stream()
                .map(cursoUsuario -> {
                    Usuario usuario = cursoUsuario.getUsuario();
                    return new UsuarioSimpleDto(usuario.getIdUsuario(), usuario.getNombreUsuario(), usuario.getSapUsuario());
                })
                .collect(Collectors.toList());
    }
}
