package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.CursoUsuarioDto;
import com.etalent.etalent_backend.entity.CursoUsuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CursoUsuarioMapperM {
    CursoUsuarioMapperM INSTANCE = Mappers.getMapper(CursoUsuarioMapperM.class);

    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "usuario.nombreUsuario", target = "nombreUsuario")
    CursoUsuarioDto toCursoUsuarioDto(CursoUsuario cursoUsuario);

    @InheritInverseConfiguration
    CursoUsuario toCursoUsuario(CursoUsuarioDto cursoUsuarioDto);

}
