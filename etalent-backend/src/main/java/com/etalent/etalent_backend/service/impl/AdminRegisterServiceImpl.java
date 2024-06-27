package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.AdminRegisterDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.mapper.AdminRegisterMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.service.AdminRegisterService;
import com.etalent.etalent_backend.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminRegisterServiceImpl implements AdminRegisterService {

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
        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        if (!passwordEncoder.matches(contraAdmin, admin.getContraAdmin())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return jwtTokenProvider.generateToken(admin.getCorreoAdmin());
    }
}
