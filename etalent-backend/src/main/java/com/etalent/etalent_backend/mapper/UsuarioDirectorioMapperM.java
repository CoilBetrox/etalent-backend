package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.UsuarioDirectorioDto;
import com.etalent.etalent_backend.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioDirectorioMapperM {
    UsuarioDirectorioMapperM INSTANCE = Mappers.getMapper(UsuarioDirectorioMapperM.class);

    UsuarioDirectorioDto toUsuarioDirectorioDto(Usuario usuario);
    Usuario toUsuario(UsuarioDirectorioDto usuarioDirectorioDto);
}
