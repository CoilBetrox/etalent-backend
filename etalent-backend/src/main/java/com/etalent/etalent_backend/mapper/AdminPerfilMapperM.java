package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.AdminPerfilDto;
import com.etalent.etalent_backend.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminPerfilMapperM {
    AdminPerfilMapperM INSTANCE = Mappers.getMapper(AdminPerfilMapperM.class);

    AdminPerfilDto toAdminPerfilDto(Admin admin);
    Admin toAdminPerfilDto(AdminPerfilDto adminPerfilDto);
}