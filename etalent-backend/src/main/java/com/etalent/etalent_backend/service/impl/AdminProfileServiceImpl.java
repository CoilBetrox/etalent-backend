package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.AdminDto;
import com.etalent.etalent_backend.dto.AdminPerfilDto;
import com.etalent.etalent_backend.dto.AdminUpdateDto;
import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.mapper.AdminMapperM;
import com.etalent.etalent_backend.mapper.AdminPerfilMapperM;
import com.etalent.etalent_backend.mapper.UsuarioMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.repository.AdminRepository;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.AdminProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminProfileServiceImpl implements AdminProfileService {

    private final UsuarioRepository usuarioRepository;
    private AdminRegisterRepository adminRegisterRepository;
    private AdminRepository adminRepository;

    @Override
    @Transactional
    public void updateAdminPartial(AdminUpdateDto adminUpdateDto,String email) {
        Admin admin = adminRegisterRepository.findByCorreoAdmin(email)
                .orElseThrow(()-> new RuntimeException("Admin no encontrado"));

        if (adminUpdateDto.getNombreAdmin() != null) {
            admin.setNombreAdmin(adminUpdateDto.getNombreAdmin());
        }
        if (adminUpdateDto.getSapAdmin() != null){
            admin.setSapAdmin(adminUpdateDto.getSapAdmin());
        }
        if (adminUpdateDto.getCorreoAdmin() != null) {
            admin.setCorreoAdmin(adminUpdateDto.getCorreoAdmin());
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
        List<Admin> admins = adminRepository.findByRolAdminsNombreRol(rolNombre);
        return admins.stream()
                .map(AdminMapperM.INSTANCE::toAdminDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> getUsuariosByAdmin(Integer adminId) {
        List<Usuario> usuarios = adminRepository.findUsuariosByAdminId(adminId);
        return usuarios.stream()
                .map(UsuarioMapperM.INSTANCE::toUsuarioDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void reassignUsers(Integer oldAdminId, Integer newAdminId) {
        Admin oldAdmin = adminRepository.findById(oldAdminId)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));
        Admin newAdmin = adminRepository.findById(newAdminId)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        List<Usuario> usuarios = usuarioRepository.findByAdmin(oldAdmin);
        for (Usuario usuario : usuarios) {
            usuario.setAdmin(newAdmin);
        }
        usuarioRepository.saveAll(usuarios);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminPerfilDto getAdminBySap(String sap) {
        Admin admin = adminRegisterRepository.findBySapAdmin(sap)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        return AdminPerfilMapperM.INSTANCE.toAdminPerfilDto(admin);
    }
}