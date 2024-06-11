package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapperM {
    UsuarioMapperM INSTANCE = Mappers.getMapper(UsuarioMapperM.class);

    UsuarioDto toUsuarioDto(Usuario usuario);
    Usuario toUsuario(UsuarioDto usuarioDto);
}
