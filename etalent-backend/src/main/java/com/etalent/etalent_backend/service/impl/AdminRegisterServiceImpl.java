package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.AdminLoginResponseDto;
import com.etalent.etalent_backend.dto.AdminRegisterDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.RolAdmin;
import com.etalent.etalent_backend.mapper.AdminRegisterMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.repository.RolAdminRepository;
import com.etalent.etalent_backend.service.AdminRegisterService;
import com.etalent.etalent_backend.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminRegisterServiceImpl implements AdminRegisterService {

    private final AuthenticationManager authenticationManager;
    private AdminRegisterRepository adminRegisterRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private RolAdminRepository rolAdminRepository;

    @Override
    @Transactional
    public AdminRegisterDto registerAdmin(AdminRegisterDto adminRegisterDto) {
        if (adminRegisterRepository.findByCorreoAdmin(adminRegisterDto.getCorreoAdmin()).isPresent()){
            throw new RuntimeException("El correo ya está registrado");
        }
        Admin admin = AdminRegisterMapperM.INSTANCE.toAdmin(adminRegisterDto);
        admin.setContraAdmin(passwordEncoder.encode(admin.getContraAdmin()));
        admin.setVerified(true);    //false si verificación por correo
        Set<RolAdmin> roles = adminRegisterDto.getRolAdmins().stream()
                .map(rolDto -> rolAdminRepository.findById(rolDto.getIdRol())
                        .orElseThrow( () -> new RuntimeException("Rol no encontrado" + rolDto.getNombreRol())))
                .collect(Collectors.toSet());
        admin.setRolAdmins(roles);

        Admin savedAdmin = adminRegisterRepository.save(admin);
        return AdminRegisterMapperM.INSTANCE.toAdminDto(savedAdmin);
    }

    @Override
    public AdminLoginResponseDto authenticateAdmin(String correoAdmin, String contraAdmin) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(correoAdmin, contraAdmin));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication.getName());


        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin).orElseThrow(() -> {
                    return new RuntimeException("Admin no encontrado");
                });

        String userRole = admin.getRolAdmins().stream().findFirst().map(
                role -> role.getNombreRol()).orElse("Desconocido");

        return new AdminLoginResponseDto(token, userRole);
    }
}