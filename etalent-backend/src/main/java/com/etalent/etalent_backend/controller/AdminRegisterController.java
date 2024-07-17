package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.AdminLoginDto;
import com.etalent.etalent_backend.dto.AdminLoginResponseDto;
import com.etalent.etalent_backend.dto.AdminRegisterDto;
import com.etalent.etalent_backend.service.AdminRegisterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/admins/auth")
public class AdminRegisterController {

    private static final Logger log = LoggerFactory.getLogger(AdminRegisterController.class);
    private AdminRegisterService adminRegisterService;

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
}
