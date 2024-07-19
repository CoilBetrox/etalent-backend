package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    //Build Post Usuario REST API
    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuarioDto) {
        UsuarioDto savedUsuario = usuarioService.createUsuario(usuarioDto);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    //Build Get Usuario by Id REST API
    @GetMapping("{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable("id") Integer usuarioId) {
        UsuarioDto usuarioDto = usuarioService.getUsuarioById(usuarioId);
        return ResponseEntity.ok(usuarioDto);
    }

    //Build Get All Usuarios REST API
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
        List<UsuarioDto> usuarioDtos = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarioDtos);
    }

    //Build Update Usuario REST API
    @PutMapping("{id}")
    public ResponseEntity<UsuarioDto> updateUsuarioRol(@PathVariable("id") Integer usuarioId,
                                                    @RequestBody UsuarioDto updatedUsuarioDto) {
        UsuarioDto usuarioDto = usuarioService.updateUsuario(usuarioId, updatedUsuarioDto);
        return ResponseEntity.ok(usuarioDto);
    }

    //Build Delete Usuario REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") Integer usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
        return ResponseEntity.ok("Usuario deleted successfully");
    }
}