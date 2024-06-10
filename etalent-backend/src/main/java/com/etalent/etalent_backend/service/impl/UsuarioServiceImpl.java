package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.UsuarioMapperM;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = UsuarioMapperM.INSTANCE.toUsuario(usuarioDto);
        Usuario saveUsuario = usuarioRepository.save(usuario);
        return UsuarioMapperM.INSTANCE.toUsuarioDto(saveUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getUsuarioById(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario is not exist wih given id: "+usuarioId));
        return UsuarioMapperM.INSTANCE.toUsuarioDto(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> getAllUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioMapperM.INSTANCE::toUsuarioDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsuarioDto updateUsuario(Integer usuarioId, UsuarioDto updatedUsuario) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new ResourceNotFoundException("Usuario is not exist wih given id: "+usuarioId));
        usuario.setNombreUsuario(updatedUsuario.getNombreUsuario());
        usuario.setCorreoUsuario(updatedUsuario.getCorreoUsuario());
        usuario.setSapUsuario(updatedUsuario.getSapUsuario());
        usuario.setEstadoUsuario(updatedUsuario.getEstadoUsuario());
        usuario.setRolUsuario(updatedUsuario.getRolUsuario());

        Usuario updatedUsuarioObj = usuarioRepository.save(usuario);
        return UsuarioMapperM.INSTANCE.toUsuarioDto(updatedUsuarioObj);
    }

    @Override
    @Transactional
    public void deleteUsuario(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new ResourceNotFoundException("Usuario is not exist wih given id: "+usuarioId)
        );
        usuarioRepository.deleteById(usuarioId);
    }
}
