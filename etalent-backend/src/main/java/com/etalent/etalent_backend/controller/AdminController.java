package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.AdminDto;
import com.etalent.etalent_backend.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private AdminService adminService;

    //Build Add Admin REST API
    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
        AdminDto savedAdmin = adminService.createAdmin(adminDto);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }
}
