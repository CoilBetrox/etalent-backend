package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.RolUsuarioDto;
import com.etalent.etalent_backend.service.RolUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/rolusuarios")
public class RolUsuarioController {

    private RolUsuarioService rolUsuarioService;

    //Build Post Rol REST API
    @PostMapping
    public ResponseEntity<RolUsuarioDto> crearRolUsuario(@RequestBody RolUsuarioDto rolUsuarioDto) {
        RolUsuarioDto savedRolUsuario = rolUsuarioService.createRolUsuario(rolUsuarioDto);
        return new ResponseEntity<>(savedRolUsuario, HttpStatus.CREATED);
    }

    //Build Get RolUsuario by ID
    @GetMapping("{id}")
    public ResponseEntity<RolUsuarioDto> getRolUsuarioById(@PathVariable("id") Integer rolUsuarioId) {
        RolUsuarioDto rolUsuarioDto = rolUsuarioService.getRolById(rolUsuarioId);
        return ResponseEntity.ok(rolUsuarioDto);
    }

    //Build Get All RolUsuarios REST API
    @GetMapping
    public ResponseEntity<List<RolUsuarioDto>> getAllUsuarios(){
        List<RolUsuarioDto> rolUsuarioDtos = rolUsuarioService.getAllRolUsuarios();
        return ResponseEntity.ok(rolUsuarioDtos);
    }

    //Build Update RolUsuario REST API
    @PutMapping("{id}")
    public ResponseEntity<RolUsuarioDto> updateRolUsuario(@PathVariable("id") Integer rolUsuarioId,
                                                          @RequestBody RolUsuarioDto updatedRolUsuarioDto) {
        RolUsuarioDto rolUsuarioDto = rolUsuarioService.updateRolUsuario(rolUsuarioId, updatedRolUsuarioDto);
        return ResponseEntity.ok(rolUsuarioDto);
    }

    //Build Delete RolUsuario REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRolUsuario(@PathVariable("id") Integer rolUsuarioId) {
        rolUsuarioService.deleteRolUsuario(rolUsuarioId);
        return ResponseEntity.ok("RolUsuario deleted succesfully");
    }
}
