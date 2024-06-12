package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.service.UsuarioRolService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/usuarioRol")
public class UsuarioRolController {

    private UsuarioRolService usuarioRolService;

    @PostMapping("/usrwr")
    public ResponseEntity<UsuarioDto> createUsuarioWithRol(@RequestBody UsuarioDto usuarioDto) {
        UsuarioDto newUsuarioDto = usuarioRolService.createUsuarioWithRol(usuarioDto);
        return ResponseEntity.ok(newUsuarioDto);
    }

}
