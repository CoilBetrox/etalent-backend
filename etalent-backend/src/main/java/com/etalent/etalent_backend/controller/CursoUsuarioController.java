package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.CursoConUsuariosDto;
import com.etalent.etalent_backend.dto.CursoUsuarioDto;
import com.etalent.etalent_backend.dto.CursoUsuarioRelacionDto;
import com.etalent.etalent_backend.dto.CursoUsuarioSimpleDto;
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
    public ResponseEntity<CursoUsuarioDto> crateCursoUsuario(@RequestBody CursoUsuarioDto cursoUsuarioDto) {
        CursoUsuarioDto savedCursoUsuario = cursoUsuarioService.createCursoUsuario(cursoUsuarioDto);
        return new ResponseEntity<>(savedCursoUsuario, HttpStatus.CREATED);
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
    public ResponseEntity<List<CursoUsuarioDto>> getAllCursos() {
        List<CursoUsuarioDto> cursos = cursoUsuarioService.getAllCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/simple")
    public ResponseEntity<List<CursoUsuarioSimpleDto>> getAllCursosSimple() {
        List<CursoUsuarioSimpleDto> cursos = cursoUsuarioService.getAllCursosSimple();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/usuarios/{idCursoUsuario}")
    public ResponseEntity<CursoConUsuariosDto> getUsuariosByCursoId(@PathVariable Integer idCursoUsuario) {
        CursoConUsuariosDto cursoConUsuarios = cursoUsuarioService.getUsuariosByCursoId(idCursoUsuario);
        return ResponseEntity.ok(cursoConUsuarios);
    }

    @PostMapping("/assign/{idCursoUsuario}/{idUsuario}")
    public ResponseEntity<CursoUsuarioRelacionDto> assignUserToCurso(
            @PathVariable Integer idCursoUsuario,
            @PathVariable Integer idUsuario) {
        CursoUsuarioRelacionDto assignedCursoUsuario = cursoUsuarioService.assignUserToCurso(idCursoUsuario, idUsuario);
        return new ResponseEntity<>(assignedCursoUsuario, HttpStatus.OK);
    }


}
