package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.AdminDto;
import com.etalent.etalent_backend.dto.AdminPerfilDto;
import com.etalent.etalent_backend.dto.AdminUpdateDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.mapper.AdminMapperM;
import com.etalent.etalent_backend.mapper.AdminPerfilMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.service.AdminProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminProfileServiceImpl implements AdminProfileService {

    private AdminRegisterRepository adminRegisterRepository;

    @Override
    @Transactional
    public void updateAdminPartial(AdminUpdateDto adminUpdateDto,String email) {
        Admin admin = adminRegisterRepository.findByCorreoAdmin(email)
                .orElseThrow(()-> new RuntimeException("Admin no encontrado"));

        if (adminUpdateDto.getProvinciaAdmin() != null) {
            admin.setProvinciaAdmin(adminUpdateDto.getProvinciaAdmin());
        }
        if (adminUpdateDto.getEmpresaAdmin() != null){
            admin.setEmpresaAdmin(adminUpdateDto.getEmpresaAdmin());
        }
        if (adminUpdateDto.getZonaAdmin() != null) {
            admin.setZonaAdmin(adminUpdateDto.getZonaAdmin());
        }
        adminRegisterRepository.save(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminPerfilDto getAdminByEmail(String email) {
        Admin admin = adminRegisterRepository.findByCorreoAdmin(email)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        return AdminPerfilMapperM.INSTANCE.toAdminPerfilDto(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminDto> getAdminsByRole(String rolNombre) {
        List<Admin> admins = adminRegisterRepository.findByRolNombre(rolNombre);
        return admins.stream()
                .map(AdminMapperM.INSTANCE::toAdminDto)
                .collect(Collectors.toList());
    }

}