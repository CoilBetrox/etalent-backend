package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.RolAdminDto;
import com.etalent.etalent_backend.service.RolAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/roladmins")
public class RolAdminController {

    private RolAdminService rolAdminService;

    //Build Add Rol REST API
    @PostMapping
    public ResponseEntity<RolAdminDto> createRol(@RequestBody RolAdminDto rolAdminDto) {
        RolAdminDto savedRol = rolAdminService.createRol(rolAdminDto);
        return new ResponseEntity<>(savedRol, HttpStatus.CREATED);
    }

    //Build Get Rol by Id REST API
    @GetMapping("{id}")
    public ResponseEntity<RolAdminDto> getRolById(@PathVariable("id") Integer rolId) {
        RolAdminDto rolAdminDto = rolAdminService.getRolById(rolId);
        return ResponseEntity.ok(rolAdminDto);
    }

    //Build Get All Rols REST API
    @GetMapping
    public ResponseEntity<List<RolAdminDto>> getAllRols() {
        List<RolAdminDto> rols = rolAdminService.getAllRols();
        return ResponseEntity.ok(rols);
    }

    //Build Update Rol REST API
    @PutMapping("{id}")
    public ResponseEntity<RolAdminDto> updateRol(@PathVariable("id") Integer RolId,
                                                 @RequestBody RolAdminDto updatedRolAdminDto) {
        RolAdminDto rolAdminDto = rolAdminService.updateRol(RolId, updatedRolAdminDto);
        return ResponseEntity.ok(rolAdminDto);
    }

    //Build Delete Rol REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRol(@PathVariable("id") Integer RolId) {
        rolAdminService.deleteRol(RolId);
        return ResponseEntity.ok("Rol deleted successfully");
    }
}
