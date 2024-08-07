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
import com.etalent.etalent_backend.service.EmailService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminRegisterServiceImpl implements AdminRegisterService {

    private final AuthenticationManager authenticationManager;
    private AdminRegisterRepository adminRegisterRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private RolAdminRepository rolAdminRepository;
    private EmailService emailService;

    private static final Logger log = LoggerFactory.getLogger(AdminRegisterServiceImpl.class);

    @Override
    @Transactional
    public AdminRegisterDto registerAdmin(AdminRegisterDto adminRegisterDto) {
        if (adminRegisterRepository.findByCorreoAdmin(adminRegisterDto.getCorreoAdmin()).isPresent()){
            throw new RuntimeException("El correo ya está registrado");
        }
        Admin admin = AdminRegisterMapperM.INSTANCE.toAdmin(adminRegisterDto);
        admin.setContraAdmin(passwordEncoder.encode(admin.getContraAdmin()));
        String verificationToken = generateVerificationToken();
        admin.setVerificationToken(verificationToken);
        admin.setVerified(false);

        Set<RolAdmin> roles = adminRegisterDto.getRolAdmins().stream()
                .map(rolDto -> rolAdminRepository.findById(rolDto.getIdRol())
                        .orElseThrow( () -> new RuntimeException("Rol no encontrado" + rolDto.getNombreRol())))
                .collect(Collectors.toSet());
        admin.setRolAdmins(roles);

        Admin savedAdmin = adminRegisterRepository.save(admin);
        emailService.sendVerificationEmail(savedAdmin.getCorreoAdmin(), verificationToken);

        AdminRegisterDto responseDto = AdminRegisterMapperM.INSTANCE.toAdminRegisterDto(savedAdmin);
        responseDto.setVerificationToken(verificationToken);

        return responseDto;
    }

    @Override
    public AdminLoginResponseDto authenticateAdmin(String correoAdmin, String contraAdmin) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(correoAdmin, contraAdmin));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication.getName());

        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin).orElseThrow(
                () -> new RuntimeException("Admin no encontrado"));

        log.info("Admin encontrado: " + admin.getIdAdmin());
        log.info("Roles asignados al admin: " + admin.getRolAdmins().size());

        Set<String> roles = admin.getRolAdmins().stream()
                .map(RolAdmin::getNombreRol)
                .collect(Collectors.toSet());

        log.info("Roles del recuperados: " + roles);
        return new AdminLoginResponseDto(token, roles);
    }

    private String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
}