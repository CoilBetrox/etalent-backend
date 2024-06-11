package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.RolAdminDto;
import com.etalent.etalent_backend.entity.RolAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolAdminMapperM {
    RolAdminMapperM INSTANCE = Mappers.getMapper(RolAdminMapperM.class);

    RolAdminDto toRolDto(RolAdmin rolAdmin);
    RolAdmin toRol(RolAdminDto rolAdminDto);
}
