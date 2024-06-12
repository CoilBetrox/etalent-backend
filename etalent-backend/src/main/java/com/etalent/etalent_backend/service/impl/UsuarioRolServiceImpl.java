package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.entity.RolUsuario;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.UsuarioMapperM;
import com.etalent.etalent_backend.repository.RolUsuarioRepository;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.UsuarioRolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UsuarioRolServiceImpl implements UsuarioRolService {

    private UsuarioRepository userRepository;
    private RolUsuarioRepository rolUsuarioRepository;

    @Override
    @Transactional
    public UsuarioDto createUsuarioWithRol(UsuarioDto usuarioDto) {
        Usuario usuario = UsuarioMapperM.INSTANCE.toUsuario(usuarioDto);

        if (usuarioDto.getRolUsuario() != null && usuarioDto.getRolUsuario().getIdRolUsuario() != null){
            RolUsuario rolUsuario = rolUsuarioRepository.findById(usuarioDto.getRolUsuario().getIdRolUsuario()).get();
            usuario.setRolUsuario(rolUsuario);
        }

        Usuario savedUsuario = userRepository.save(usuario);
        return UsuarioMapperM.INSTANCE.toUsuarioDto(savedUsuario);
    }
}
