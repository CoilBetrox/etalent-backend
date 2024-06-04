package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.AdminDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.AdminMapper;
import com.etalent.etalent_backend.repository.AdminRepository;
import com.etalent.etalent_backend.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Admin admin = AdminMapper.mapToAdmin(adminDto);
        Admin savedAdmin = adminRepository.save(admin);
        return AdminMapper.mapToAdminDto(savedAdmin);
    }

    @Override
    public AdminDto getAdminById(int adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin is not exist with given id:"+adminId));
        return AdminMapper.mapToAdminDto(admin);
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map((admin) -> AdminMapper.mapToAdminDto(admin))
                .collect(Collectors.toList());
    }

    @Override
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
        admin.setRolAdmin(updatedAdmin.getRolAdmin());

        Admin updatedAdminObj = adminRepository.save(admin);
        return AdminMapper.mapToAdminDto(updatedAdminObj);
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new ResourceNotFoundException("Admin is not exist with given id:"+adminId)
        );
        adminRepository.deleteById(adminId);
    }
}
