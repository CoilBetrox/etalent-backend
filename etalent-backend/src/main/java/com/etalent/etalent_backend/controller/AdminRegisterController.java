package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.AdminLoginDto;
import com.etalent.etalent_backend.dto.AdminLoginResponseDto;
import com.etalent.etalent_backend.dto.AdminRegisterDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.service.AdminRegisterService;
import com.etalent.etalent_backend.service.EmailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

//@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/admins/auth")
public class AdminRegisterController {

    private static final Logger log = LoggerFactory.getLogger(AdminRegisterController.class);
    private AdminRegisterService adminRegisterService;
    private EmailService emailService;
    private PasswordEncoder passwordEncoder;
    private AdminRegisterRepository adminRegisterRepository;

    @PostMapping("/register")
    public ResponseEntity<AdminRegisterDto> createAdmin(@RequestBody AdminRegisterDto adminRegisterDto){
        AdminRegisterDto savedAdmin = adminRegisterService.registerAdmin(adminRegisterDto);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminLoginDto adminLoginDto){
        try {
            AdminLoginResponseDto response = adminRegisterService.authenticateAdmin(adminLoginDto.getCorreoAdmin(), adminLoginDto.getContraAdmin());
            log.info("Respuesta de login:" + response);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (RuntimeException e){
            log.error("Error durante el login: ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {
        log.debug("Attempting to verify email with token: {}", token);
        try {
            Optional<Admin> adminOptional = adminRegisterRepository.findByVerificationToken(token);
                    //.orElseThrow(() -> new ResourceNotFoundException("Token de verificación invalido"));

            if (adminOptional.isPresent()){
                Admin admin = adminOptional.get();
                admin.setVerified(true);
                admin.setVerificationToken(null);
                adminRegisterRepository.save(admin);
                log.info("Email verified successfully for admin: {}", admin.getCorreoAdmin());
                return ResponseEntity.ok("Correo verificado exitosamente. Ya puede iniciar sesión. Pronto será dirigido.");
            }else {
                log.warn("No admin found with verification token: {}", token);
                throw new ResourceNotFoundException("Token de verificación inválido");
            }
        } catch (Exception e) {
            log.error("Error verifying email: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al verificar el correo");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        Admin admin = adminRegisterRepository.findByCorreoAdmin(email)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario con ese correo electrónico"));

        String resetToken = UUID.randomUUID().toString();
        admin.setPasswordResetToken(resetToken);
        admin.setPasswordResetTokenExpiry(new Date(System.currentTimeMillis() + 3600000)); // 1 hora de validez
        adminRegisterRepository.save(admin);

        emailService.sendPasswordResetEmail(admin.getCorreoAdmin(), resetToken);

        return ResponseEntity.ok("Se ha enviado un correo electrónico con instrucciones para restablecer la contraseña");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Admin admin = adminRegisterRepository.findByPasswordResetToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token de restablecimiento inválido"));

        if (admin.getPasswordResetTokenExpiry().before(new Date())) {
            throw new RuntimeException("El token de restablecimiento ha expirado");
        }

        admin.setContraAdmin(passwordEncoder.encode(newPassword));
        admin.setPasswordResetToken(null);
        admin.setPasswordResetTokenExpiry(null);
        adminRegisterRepository.save(admin);

        return ResponseEntity.ok("Contraseña restablecida con éxito");
    }
}
