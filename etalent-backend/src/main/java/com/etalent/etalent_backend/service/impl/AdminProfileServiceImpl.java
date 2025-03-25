package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.*;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.RolAdmin;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.AdminMapperM;
import com.etalent.etalent_backend.mapper.AdminPerfilMapperM;
import com.etalent.etalent_backend.mapper.AdminRegisterMapperM;
import com.etalent.etalent_backend.mapper.UsuarioMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.repository.AdminRepository;
import com.etalent.etalent_backend.repository.RolAdminRepository;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.AdminProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminProfileServiceImpl implements AdminProfileService {

    private final UsuarioRepository usuarioRepository;
    private AdminRegisterRepository adminRegisterRepository;
    private AdminRepository adminRepository;
    private RolAdminRepository rolAdminRepository;

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
        if (adminUpdateDto.getEmpresaAdmin() != null) {
            admin.setEmpresaAdmin(adminUpdateDto.getEmpresaAdmin());
        }
        if (adminUpdateDto.getProvinciaAdmin() != null) {
            admin.setProvinciaAdmin(adminUpdateDto.getProvinciaAdmin());
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

        List<Usuario> usuariosOldAdmin = usuarioRepository.findByAdmin(oldAdmin);
        List<Usuario> usuariosNewAdmin = usuarioRepository.findByAdmin(newAdmin);

        for (Usuario usuario : usuariosOldAdmin) {
            usuario.setAdmin(newAdmin);
        }

        for (Usuario usuario : usuariosNewAdmin) {
            usuario.setAdmin(oldAdmin);
        }
        usuarioRepository.saveAll(usuariosOldAdmin);
        usuarioRepository.saveAll(usuariosNewAdmin);

    }

    @Override
    @Transactional(readOnly = true)
    public AdminPerfilDto getAdminBySap(String sap) {
        Admin admin = adminRegisterRepository.findBySapAdmin(sap)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        return AdminPerfilMapperM.INSTANCE.toAdminPerfilDto(admin);
    }

    @Override
    public AdminPerfilDto updateAdminEstado(Integer adminId, AdminPerfilDto adminUpdatedto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoAdmin = authentication.getName();
        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(()-> new RuntimeException("Admin no encontrado"));
        Admin adminExistente = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin is not exist with given id: "+adminId));

        adminExistente.setEstadoAdmin(adminUpdatedto.getEstadoAdmin());

        Admin updatedAdminObj = adminRepository.save(adminExistente);


        return AdminPerfilMapperM.INSTANCE.toAdminPerfilDto(updatedAdminObj);
    }

    @Override
    @Transactional
    public List<AdminRegisterDto> createJefesBulk(List<AdminRegisterDto> adminsDtos) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoAdmin = authentication.getName();
        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        List<AdminRegisterDto> savedAdmins = new ArrayList<>();

        RolAdmin rolAdminTienda = rolAdminRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Rol AdminTienda no encontrado"));

        for (AdminRegisterDto adminDto : adminsDtos) {
            // Verificar si el correo ya existe
            if (adminRegisterRepository.findByCorreoAdmin(adminDto.getCorreoAdmin()).isPresent()) {
                throw new RuntimeException("El correo " + adminDto.getCorreoAdmin() + " ya está registrado");
            }

            // Convertir DTO a entidad
            Admin nuevoAdmin = AdminRegisterMapperM.INSTANCE.toAdmin(adminDto);
            nuevoAdmin.setVerified(true);

            Set<RolAdmin> roles = new HashSet<>();
            roles.add(rolAdminTienda);

            nuevoAdmin.setRolAdmins((roles));


            // Guardar admin
            Admin savedAdmin = adminRegisterRepository.save(nuevoAdmin);

            // Convertir a DTO de respuesta
            AdminRegisterDto responseDto = AdminRegisterMapperM.INSTANCE.toAdminRegisterDto(savedAdmin);
            savedAdmins.add(responseDto);
        }

        return savedAdmins;
    }

}