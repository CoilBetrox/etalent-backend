package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.AdminDto;
import com.etalent.etalent_backend.entity.Admin;

public class AdminMapper {

    public static AdminDto mapToAdminDto(Admin admin) {
        return new AdminDto(
                admin.getIdAdmin(),
                admin.getNombreAdmin(),
                admin.getSapAdmin(),
                admin.getCorreoAdmin(),
                admin.getImagenAdmin(),
                admin.getEmpresaAdmin(),
                admin.getProvinciaAdmin(),
                admin.getZonaAdmin(),
                admin.getEstadoAdmin(),
                admin.getRol()
        );
    }

    public static Admin mapToAdmin(AdminDto adminDto) {
        return new Admin(
                adminDto.getIdAdmin(),
                adminDto.getNombreAdmin(),
                adminDto.getSapAdmin(),
                adminDto.getCorreoAdmin(),
                adminDto.getImagenAdmin(),
                adminDto.getEmpresaAdmin(),
                adminDto.getProvinciaAdmin(),
                adminDto.getZonaAdmin(),
                adminDto.getEstadoAdmin(),
                adminDto.getRol()
        );
    }
}
