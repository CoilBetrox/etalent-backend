package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.AdminDto;
import com.etalent.etalent_backend.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
//@RequestMapping("/api/admins")
public class AdminController {

    /*
    private AdminService adminService;

    //Build Post Admin REST API
    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
        AdminDto savedAdmin = adminService.createAdmin(adminDto);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    //Build Get Admin by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable("id") Integer adminId) {
        AdminDto adminDto = adminService.getAdminById(adminId);
        return ResponseEntity.ok(adminDto);
    }

    //Build Get All Admins REST API
    @GetMapping
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> adminDtos = adminService.getAllAdmins();
        return ResponseEntity.ok(adminDtos);
    }

    //Build Update Admin REST API
    @PutMapping("{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable("id") Integer adminId,
                                                @RequestBody AdminDto updatedAdminDto) {
            AdminDto adminDto = adminService.updateAdmin(adminId, updatedAdminDto);
            return ResponseEntity.ok(adminDto);
    }

    //Build Delete Admin REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Integer AdminId) {
        adminService.deleteAdmin(AdminId);
        return ResponseEntity.ok("Admin deleted succesfully");
    }

    //Build Put Admin and Rol with id REST API
    @PutMapping("/{adminId}/roladmin/{rolId}")
    public ResponseEntity<AdminDto> addRolToAdmin(@PathVariable Integer adminId, @PathVariable Integer rolId){
        AdminDto updatedAdmin = adminService.addRolToAdmin(adminId, rolId);
        return ResponseEntity.ok(updatedAdmin);
    }


     */
}