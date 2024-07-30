package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.CursoUsuarioDto;
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
    public CursoUsuarioDto createCursoUsuario(CursoUsuarioDto cursoUsuarioDto, Integer idUsuario) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoAdmin = authentication.getName();
        Admin admin =  adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));
        CursoUsuario cursoUsuario = CursoUsuarioMapperM.INSTANCE.toCursoUsuario(cursoUsuarioDto);
        Usuario usuario = usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.getCursosUsuario().add(cursoUsuario);
        cursoUsuario.getUsuarios().add(usuario);

        cursoUsuarioRepository.save(cursoUsuario);
        usuarioRepository.save(usuario);

        return CursoUsuarioMapperM.INSTANCE.toCursoUsuarioDto(cursoUsuario);
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
}
