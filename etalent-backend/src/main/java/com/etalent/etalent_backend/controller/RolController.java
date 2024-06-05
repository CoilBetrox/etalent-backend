package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.RolDto;
import com.etalent.etalent_backend.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/rols")
public class RolController {

    private RolService rolService;

    //Build Add Rol REST API
    @PostMapping
    public ResponseEntity<RolDto> createRol(@RequestBody RolDto rolDto) {
        RolDto savedRol = rolService.createRol(rolDto);
        return new ResponseEntity<>(savedRol, HttpStatus.CREATED);
    }

    //Build Get Rol by Id REST API
    @GetMapping("{id}")
    public ResponseEntity<RolDto> getRolById(@PathVariable("id") Integer rolId) {
        RolDto rolDto = rolService.getRolById(rolId);
        return ResponseEntity.ok(rolDto);
    }

    //Build Get All Rols REST API
    @GetMapping
    public ResponseEntity<List<RolDto>> getAllRols() {
        List<RolDto> rols = rolService.getAllRols();
        return ResponseEntity.ok(rols);
    }

    //Build Update Rol REST API
    @PutMapping("{id}")
    public ResponseEntity<RolDto> updateRol(@PathVariable("id") Integer RolId,
                                            @RequestBody RolDto updatedRol) {
        RolDto rolDto = rolService.updateRol(RolId, updatedRol);
        return ResponseEntity.ok(rolDto);
    }

    public ResponseEntity<String> deleteRol(@PathVariable("id") Integer RolId) {
        rolService.deleteRol(RolId);
        return ResponseEntity.ok("Rol deleted successfully");
    }
}
