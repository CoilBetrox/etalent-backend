package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.CursoConUsuariosDto;
import com.etalent.etalent_backend.dto.CursoUsuarioDto;
import com.etalent.etalent_backend.dto.CursoUsuarioSimpleDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.CursoUsuario;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.mapper.CursoUsuarioMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
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

    @Override
    @Transactional
    public CursoUsuarioDto createCursoUsuario(CursoUsuarioDto cursoUsuarioDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoAdmin = authentication.getName();
        Admin admin =  adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));
        CursoUsuario cursoUsuario = CursoUsuarioMapperM.INSTANCE.toCursoUsuario(cursoUsuarioDto);
        return CursoUsuarioMapperM.INSTANCE.toCursoUsuarioDto(cursoUsuarioRepository.save(cursoUsuario));
    }

    @Override
    @Transactional
    public CursoUsuarioDto assignUserToCurso(Integer idCursoUsuario, Integer idUsuario) {
        return null;
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
    public List<CursoUsuarioDto> getAllCursos() {
        List<CursoUsuario> cursos = cursoUsuarioRepository.findAll();
        return CursoUsuarioMapperM.INSTANCE.toCursoUsuarioDtoList(cursos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoUsuarioSimpleDto> getAllCursosSimple() {
        List<CursoUsuario> cursos = cursoUsuarioRepository.findAll();
        return CursoUsuarioMapperM.INSTANCE.toCursoUsuarioSimpleDtoList(cursos);
    }

    @Override
    @Transactional(readOnly = true)
    public CursoConUsuariosDto getUsuariosByCursoId(Integer idCursoUsuario) {
        CursoUsuario cursoUsuario = cursoUsuarioRepository.findById(idCursoUsuario)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        return CursoUsuarioMapperM.INSTANCE.toCursoConUsuariosDto(cursoUsuario);
    }
}
