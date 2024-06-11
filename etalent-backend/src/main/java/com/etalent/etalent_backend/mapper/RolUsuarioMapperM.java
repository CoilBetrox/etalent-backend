package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.RolUsuarioDto;
import com.etalent.etalent_backend.entity.RolUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolUsuarioMapperM {
    RolUsuarioMapperM INSTANCE = Mappers.getMapper(RolUsuarioMapperM.class);

    RolUsuarioDto toRolUsuarioDto(RolUsuario rolUsuario);
    RolUsuario toRolUsuario(RolUsuarioDto rolUsuarioDto);
}
