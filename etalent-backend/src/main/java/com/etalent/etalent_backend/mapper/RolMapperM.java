package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.RolDto;
import com.etalent.etalent_backend.entity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolMapperM {

    RolMapperM INSTANCE = Mappers.getMapper(RolMapperM.class);

    RolDto toRolDto(Rol rol);
    Rol toRol(RolDto rolDto);
}
