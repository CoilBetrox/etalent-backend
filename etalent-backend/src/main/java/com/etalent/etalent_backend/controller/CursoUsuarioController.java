package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.CursoUsuarioDto;
import com.etalent.etalent_backend.service.CursoUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cursosUsuario")
public class CursoUsuarioController {

    private CursoUsuarioService cursoUsuarioService;

    @PostMapping("{usuarioId}")
    public ResponseEntity<CursoUsuarioDto> crateCursoUsuario(@RequestBody CursoUsuarioDto cursoUsuarioDto, @PathVariable Integer usuarioId) {
        CursoUsuarioDto savedCursoUsuario = cursoUsuarioService.createCursoUsuario(cursoUsuarioDto, usuarioId);
        return new ResponseEntity<>(savedCursoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("{usuarioId}")
    public ResponseEntity<List<CursoUsuarioDto>> getCursosByIdUsuario(@PathVariable Integer usuarioId) {
        List<CursoUsuarioDto> cursosUsuario = cursoUsuarioService.getCursosByIdUsuario(usuarioId);
        return ResponseEntity.ok(cursosUsuario);
    }

    ////
    //// Aquí me quedé
    ////
    @PutMapping("{idCursoUsuario}")
    public ResponseEntity<CursoUsuarioDto> updateCursoUsuario(@PathVariable Integer idCusoUsuario, @RequestBody CursoUsuarioDto cursoUsuarioDto ) {
        CursoUsuarioDto updatedCursUsuario = cursoUsuarioService.updateCursoUsuario(
                idCusoUsuario, cursoUsuarioDto.getAvanceCurso(), cursoUsuarioDto.getEstadoCurso());
        return ResponseEntity.ok(updatedCursUsuario);
    }

}
