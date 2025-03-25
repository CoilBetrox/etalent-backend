package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.*;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.Curso;
import com.etalent.etalent_backend.entity.CursoUsuario;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.mapper.CursoMapperM;
import com.etalent.etalent_backend.mapper.CursoUsuarioMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.repository.CursoRepository;
import com.etalent.etalent_backend.repository.CursoUsuarioRepository;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.CursoUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CursoUsuarioServiceImpl implements CursoUsuarioService {

    private CursoUsuarioRepository cursoUsuarioRepository;
    private AdminRegisterRepository adminRegisterRepository;
    private UsuarioRepository usuarioRepository;
    private CursoRepository cursoRepository;

    @Override
    @Transactional
    public CursoDto createCursoUsuario(CursoDto cursoDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoAdmin = authentication.getName();
        Admin admin =  adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        Curso curso = CursoMapperM.INSTANCE.toCurso(cursoDto);
        Curso savedCurso = cursoRepository.save(curso);
        return CursoMapperM.INSTANCE.toCursoDto(savedCurso);
    }

    @Override
    @Transactional
    public CursoUsuarioRelacionDto assignUserToCurso(Integer idCurso, Integer idUsuario) {

        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CursoUsuario nuevoCursoUsuario = new CursoUsuario();
        nuevoCursoUsuario.setCurso(curso);
        nuevoCursoUsuario.setUsuario(usuario);
        nuevoCursoUsuario.setNombreCursoUsuario(curso.getNombreCurso());
        nuevoCursoUsuario.setDescripcionCurso(curso.getDescripcion());
        nuevoCursoUsuario.setEstadoCurso("Activo");
        nuevoCursoUsuario.setAvanceCurso(0);
        nuevoCursoUsuario.setFechaInicio(curso.getFechaInicio());
        nuevoCursoUsuario.setFechaFin(curso.getFechaFin());

        CursoUsuario savedCursoUsuario = cursoUsuarioRepository.save(nuevoCursoUsuario);

        return CursoUsuarioMapperM.INSTANCE.toCursoUsuarioRelacionDto(savedCursoUsuario);
    }

    @Override
    @Transactional
    public void quitarUsuarioDeCurso(Integer idCursoUsuario, Integer idUsuario) {
        CursoUsuario cursoUsuario = cursoUsuarioRepository.findById(idCursoUsuario)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Simply set usuarios to null or remove the specific curso usuario
        cursoUsuario.setUsuario(null);
        cursoUsuarioRepository.save(cursoUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoUsuarioDto> getCursosByIdUsuario(Integer idUsuario) {

        List<CursoUsuario> cursoUsuario = cursoUsuarioRepository.findCursosByUsuarioId(idUsuario);

        return cursoUsuario.stream()
                .map(CursoUsuarioMapperM.INSTANCE::toCursoUsuarioDto)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public CursoUsuarioDto updateCursoUsuario(Integer idCursoUsuario, Integer avanceCurso, String estadoCurso) {
        CursoUsuario cursoUsuario = cursoUsuarioRepository.findById(idCursoUsuario)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        cursoUsuario.setAvanceCurso(avanceCurso);
        cursoUsuario.setEstadoCurso(estadoCurso);

        CursoUsuario updateCursoUsuario = cursoUsuarioRepository.save(cursoUsuario);
        return CursoUsuarioMapperM.INSTANCE.toCursoUsuarioDto(updateCursoUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoDto> getAllCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return CursoMapperM.INSTANCE.toCursoDtoList(cursos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoDto> getAllCursosSimple() {
        List<Curso> cursos = cursoRepository.findAll();
        return CursoMapperM.INSTANCE.toCursoDtoList(cursos);
    }

    @Override
    @Transactional(readOnly = true)
    public CursoConUsuariosDto getUsuarioByCursoId(Integer idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        List<UsuarioSimpleDto> usuarios = curso.getCursosUsuario().stream()
                .map(usuario -> new UsuarioSimpleDto(usuario.getUsuario().getIdUsuario(), usuario.getUsuario().getNombreUsuario(), usuario.getUsuario().getSapUsuario()))
                .collect(Collectors.toList());

        return new CursoConUsuariosDto(curso.getIdCurso(), curso.getNombreCurso(), usuarios);
    }
}
