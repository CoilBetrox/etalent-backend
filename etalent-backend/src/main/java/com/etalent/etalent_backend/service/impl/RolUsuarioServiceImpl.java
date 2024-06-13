package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.RolUsuarioDto;
import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.entity.RolUsuario;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.RolUsuarioMapperM;
import com.etalent.etalent_backend.mapper.UsuarioMapperM;
import com.etalent.etalent_backend.repository.RolUsuarioRepository;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.RolUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RolUsuarioServiceImpl implements RolUsuarioService {

    private final RolUsuarioRepository rolUsuarioRepository;
    private UsuarioRepository userRepository;

    @Override
    @Transactional
    public RolUsuarioDto createRolUsuario(RolUsuarioDto rolUsuarioDto) {
        return RolUsuarioMapperM.INSTANCE.toRolUsuarioDto(rolUsuarioRepository.save(RolUsuarioMapperM.INSTANCE.toRolUsuario(rolUsuarioDto)));
    }

    @Override
    @Transactional(readOnly = true)
    public RolUsuarioDto getRolById(Integer rolUsuarioId) {
        RolUsuario rolUsuario = rolUsuarioRepository.findById(rolUsuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("RolUsuario is not exist with given id: " + rolUsuarioId));
        return RolUsuarioMapperM.INSTANCE.toRolUsuarioDto(rolUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolUsuarioDto> getAllRolUsuarios() {
        List<RolUsuario> rolUsuarios = rolUsuarioRepository.findAll();
        return rolUsuarios.stream().map(RolUsuarioMapperM.INSTANCE::toRolUsuarioDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RolUsuarioDto updateRolUsuario(Integer rolUsuarioId, RolUsuarioDto updatedRolUsuario) {
        RolUsuario rolUsuario = rolUsuarioRepository.findById(rolUsuarioId).orElseThrow(
                () -> new ResourceNotFoundException("RolUsuario is not exist with given id: " + rolUsuarioId)
        );
        rolUsuario.setNombreRolUsuario(updatedRolUsuario.getNombreRolUsuario());

        RolUsuario updateRolUsuarioObj = rolUsuarioRepository.save(rolUsuario);
        return RolUsuarioMapperM.INSTANCE.toRolUsuarioDto(updateRolUsuarioObj);
    }

    @Override
    @Transactional
    public void deleteRolUsuario(Integer rolUsuaioId) {
        RolUsuario rolUsuario = rolUsuarioRepository.findById(rolUsuaioId).orElseThrow(
                () -> new ResourceNotFoundException("RolUsuario is not exist with given id: " + rolUsuaioId)
        );
        rolUsuarioRepository.deleteById(rolUsuaioId);
    }

    @Override
    @Transactional
    public UsuarioDto createUsuarioWithRol(UsuarioDto usuarioDto) {
        Usuario usuario = UsuarioMapperM.INSTANCE.toUsuario(usuarioDto);

        if (usuarioDto.getRolUsuario() != null && usuarioDto.getRolUsuario().getIdRolUsuario() != null){
            RolUsuario rolUsuario = rolUsuarioRepository.findById(usuarioDto.getRolUsuario().getIdRolUsuario())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("RolUsuario is not exist with given id: " + usuarioDto.getRolUsuario().getIdRolUsuario())
                    );
            usuario.setRolUsuario(rolUsuario);
        }

        Usuario savedUsuario = userRepository.save(usuario);
        return UsuarioMapperM.INSTANCE.toUsuarioDto(savedUsuario);
    }

    @Override
    public UsuarioDto updateRolUsuario(Integer usuarioId, Integer rolUsuarioId) {
        Usuario usuario = userRepository.findById(usuarioId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Usuario is not exist with given id: "+usuarioId)
                );

        RolUsuario rolUsuario = rolUsuarioRepository.findById(rolUsuarioId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("RolUsuario is not exist with given id: "+rolUsuarioId)
                );
        usuario.setRolUsuario(rolUsuario);
        Usuario savedUsuario = userRepository.save(usuario);
        return UsuarioMapperM.INSTANCE.toUsuarioDto(savedUsuario);
    }
}
