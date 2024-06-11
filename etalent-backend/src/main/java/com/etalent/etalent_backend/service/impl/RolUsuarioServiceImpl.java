package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.RolUsuarioDto;
import com.etalent.etalent_backend.entity.RolUsuario;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.RolUsuarioMapperM;
import com.etalent.etalent_backend.repository.RolUsuarioRepository;
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
}
