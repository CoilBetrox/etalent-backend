package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.AdminRegisterDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.mapper.AdminRegisterMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.service.AdminRegisterService;
import com.etalent.etalent_backend.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminRegisterServiceImpl implements AdminRegisterService {

    private static final Logger log = LoggerFactory.getLogger(AdminRegisterServiceImpl.class);
    private AdminRegisterRepository adminRegisterRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public AdminRegisterDto registerAdmin(AdminRegisterDto adminRegisterDto) {
        if (adminRegisterRepository.findByCorreoAdmin(adminRegisterDto.getCorreoAdmin()).isPresent()){
            throw new RuntimeException("El correo ya está registrado");
        }
        Admin admin = AdminRegisterMapperM.INSTANCE.toAdmin(adminRegisterDto);
        admin.setContraAdmin(passwordEncoder.encode(admin.getContraAdmin()));
        admin.setVerified(true);    //false si verificación por correo

        Admin savedAdmin = adminRegisterRepository.save(admin);
        return AdminRegisterMapperM.INSTANCE.toAdminDto(savedAdmin);
    }

    @Override
    public String authenticateAdmin(String correoAdmin, String contraAdmin) {
        log.info("Intentando autenticar admin: {}", correoAdmin);
        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(() -> {
                    log.error("Admin no encontrado: {}", correoAdmin);
                    return new RuntimeException("Admin no encontrado");
                });

        if (!passwordEncoder.matches(contraAdmin, admin.getContraAdmin())) {
            log.error("Contraseña incorrecta para admin: {}", correoAdmin);
            throw new RuntimeException("Contraseña incorrecta");
        }
        log.info("Autenticación exitosa para admin: {}", correoAdmin);
        try {
            return jwtTokenProvider.generateToken(admin.getCorreoAdmin());
        }catch (Exception e){
            log.error("Error al generar el token: {}", correoAdmin, e);
            throw new RuntimeException("Error al generar el token", e);
        }

    }
}
