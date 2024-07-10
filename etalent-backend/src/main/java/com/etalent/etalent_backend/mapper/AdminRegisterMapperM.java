package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.AdminRegisterDto;
import com.etalent.etalent_backend.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminRegisterMapperM {
    AdminRegisterMapperM INSTANCE = Mappers.getMapper(AdminRegisterMapperM.class);

    AdminRegisterDto toAdminDto(Admin admin);
    Admin toAdmin(AdminRegisterDto adminRegisterDto);
}