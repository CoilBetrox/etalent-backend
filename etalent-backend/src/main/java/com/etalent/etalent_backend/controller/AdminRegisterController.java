package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.AdminLoginDto;
import com.etalent.etalent_backend.dto.AdminRegisterDto;
import com.etalent.etalent_backend.service.AdminRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/admins/r")
public class AdminRegisterController {

    private AdminRegisterService adminRegisterService;

    @PostMapping("/register")
    public ResponseEntity<AdminRegisterDto> createAdmin(@RequestBody AdminRegisterDto adminRegisterDto){
        AdminRegisterDto savedAdmin = adminRegisterService.registerAdmin(adminRegisterDto);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody AdminLoginDto adminLoginDto){
        String token = adminRegisterService.authenticateAdmin(adminLoginDto.getCorreoAdmin(), adminLoginDto.getContraAdmin());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
