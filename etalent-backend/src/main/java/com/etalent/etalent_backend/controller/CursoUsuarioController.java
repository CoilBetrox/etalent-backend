package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.*;
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

    @PostMapping()
    public ResponseEntity<CursoDto> crateCursoUsuario(@RequestBody CursoDto cursoDto) {
        CursoDto savedCurso = cursoUsuarioService.createCursoUsuario(cursoDto);
        return new ResponseEntity<>(savedCurso, HttpStatus.CREATED);
    }

    @GetMapping("{usuarioId}")
    public ResponseEntity<List<CursoUsuarioDto>> getCursosByIdUsuario(@PathVariable Integer usuarioId) {
        List<CursoUsuarioDto> cursosUsuario = cursoUsuarioService.getCursosByIdUsuario(usuarioId);
        return ResponseEntity.ok(cursosUsuario);
    }

    @PutMapping("{idCursoUsuario}")
    public ResponseEntity<CursoUsuarioDto> updateCursoUsuario(@PathVariable Integer idCursoUsuario, @RequestBody CursoUsuarioDto cursoUsuarioDto ) {
        CursoUsuarioDto updatedCursUsuario = cursoUsuarioService.updateCursoUsuario(
                idCursoUsuario, cursoUsuarioDto.getAvanceCurso(), cursoUsuarioDto.getEstadoCurso());
        return ResponseEntity.ok(updatedCursUsuario);
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> getAllCursos() {
        List<CursoDto> cursos = cursoUsuarioService.getAllCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/simple")
    public ResponseEntity<List<CursoDto>> getAllCursosSimple() {
        List<CursoDto> cursos = cursoUsuarioService.getAllCursosSimple();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/usuarios/{idCursoUsuario}")
    public ResponseEntity<CursoConUsuariosDto> getUsuariosByCursoId(@PathVariable Integer idCursoUsuario) {
        CursoConUsuariosDto cursoConUsuarios = cursoUsuarioService.getUsuarioByCursoId(idCursoUsuario);
        return ResponseEntity.ok(cursoConUsuarios);
    }

    @PostMapping("/assign/{idCursoUsuario}/{idUsuario}")
    public ResponseEntity<CursoUsuarioRelacionDto> assignUserToCurso(
            @PathVariable Integer idCursoUsuario,
            @PathVariable Integer idUsuario) {
        CursoUsuarioRelacionDto assignedCursoUsuario = cursoUsuarioService.assignUserToCurso(idCursoUsuario, idUsuario);
        return new ResponseEntity<>(assignedCursoUsuario, HttpStatus.OK);
    }

    @DeleteMapping("/del/{idCursoUsuario}/{idUsuario}")
    public ResponseEntity<?> quitarUsuarioDeCurso(
            @PathVariable Integer idCursoUsuario,
            @PathVariable Integer idUsuario) {
        try {
            cursoUsuarioService.quitarUsuarioDeCurso(idCursoUsuario, idUsuario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
