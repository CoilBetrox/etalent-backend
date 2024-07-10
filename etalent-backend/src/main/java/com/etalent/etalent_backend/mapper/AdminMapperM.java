package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.AdminDto;
import com.etalent.etalent_backend.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapperM {
    AdminMapperM INSTANCE = Mappers.getMapper(AdminMapperM.class);

    AdminDto toAdminDto(Admin admin);
    Admin toAdmin(AdminDto adminDto);
}