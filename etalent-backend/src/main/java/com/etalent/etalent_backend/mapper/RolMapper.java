package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.RolDto;
import com.etalent.etalent_backend.entity.Rol;

public class RolMapper {

    public static RolDto mapToRolDto(Rol rol) {
        return new RolDto(
                rol.getIdRol(),
                rol.getNombreRol()
        );
    }

    public static Rol mapToRol(RolDto rolDto) {
        return new Rol(
                rolDto.getIdRol(),
                rolDto.getNombreRol()
        );
    }
}
