package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.AdminCreateRequestDto;
import com.etalent.etalent_backend.dto.AdminDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.RolAdmin;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.AdminMapperM;
import com.etalent.etalent_backend.mapper.RolAdminMapperM;
import com.etalent.etalent_backend.repository.AdminRepository;
import com.etalent.etalent_backend.repository.RolAdminRepository;
import com.etalent.etalent_backend.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private RolAdminRepository rolAdminRepository;
    private AdminRepository adminRepository;

    @Override
    @Transactional
    public AdminDto createAdmin(AdminDto adminDto) {
        return AdminMapperM.INSTANCE.toAdminDto(adminRepository.save(AdminMapperM.INSTANCE.toAdmin(adminDto)));
    }

    @Override
    @Transactional(readOnly = true)
    public AdminDto getAdminById(Integer adminId) {
        return AdminMapperM.INSTANCE.toAdminDto(adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin is not exist with given id:"+adminId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminDto> getAllAdmins() {
        return adminRepository.findAll().stream().map(AdminMapperM.INSTANCE::toAdminDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AdminDto updateAdmin(Integer adminId, AdminDto updatedAdmin) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new ResourceNotFoundException("Admin is not exist with given id:"+adminId));
        //updates
        admin.setNombreAdmin(updatedAdmin.getNombreAdmin());
        admin.setSapAdmin(updatedAdmin.getSapAdmin());
        admin.setCorreoAdmin(updatedAdmin.getCorreoAdmin());
        admin.setImagenAdmin(updatedAdmin.getImagenAdmin());
        admin.setEmpresaAdmin(updatedAdmin.getEmpresaAdmin());
        admin.setProvinciaAdmin(updatedAdmin.getProvinciaAdmin());
        admin.setZonaAdmin(updatedAdmin.getZonaAdmin());
        admin.setEstadoAdmin(updatedAdmin.getEstadoAdmin());
        admin.setRolAdmins(updatedAdmin.getRolAdmins().stream().map(RolAdminMapperM.INSTANCE::toRol).collect(Collectors.toSet()));

        Admin updatedAdminObj = adminRepository.save(admin);
        return AdminMapperM.INSTANCE.toAdminDto(updatedAdminObj);
    }

    @Override
    @Transactional
    public void deleteAdmin(Integer adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new ResourceNotFoundException("Admin is not exist with given id:"+adminId)
        );
        adminRepository.deleteById(adminId);
    }

    @Override
    @Transactional
    public AdminDto addRolToAdmin(Integer adminId, Integer rolId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin is not exist with given id:"+adminId));
        RolAdmin rol = rolAdminRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("Rol is not exist with given id:"+rolId));
        admin.addRol(rol);
        Admin updatedAdminObj = adminRepository.save(admin);
        return AdminMapperM.INSTANCE.toAdminDto(updatedAdminObj);
    }

    @Override
    @Transactional
    public AdminDto createAdminWithRoles(AdminDto adminDto) {
        Admin admin  = AdminMapperM.INSTANCE.toAdmin(adminDto);

        if (adminDto.getRolAdmins() != null){
            Set<RolAdmin> roles = adminDto.getRolAdmins().stream()
                    .map(rolDto -> rolAdminRepository.findById(rolDto.getIdRol())
                            .orElseThrow(
                                    () -> new ResourceNotFoundException("RolAdmin not found with id: "+rolDto.getIdRol())
                            )).collect(Collectors.toSet());
            admin.setRolAdmins(roles);
        }

        Admin savedAdmin = adminRepository.save(admin);
        return AdminMapperM.INSTANCE.toAdminDto(savedAdmin);
    }
}
