package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.RolDto;
import com.etalent.etalent_backend.entity.Rol;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.RolMapperM;
import com.etalent.etalent_backend.repository.RolRepository;
import com.etalent.etalent_backend.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RolServiceImpl implements RolService {

    private RolRepository rolRepository;

    @Override
    @Transactional
    public RolDto createRol(RolDto rolDto) {
        //Rol rol = RolMapper.mapToRol(rolDto);
        Rol rol = RolMapperM.INSTANCE.toRol(rolDto);
        Rol savedRol = rolRepository.save(rol);
        return RolMapperM.INSTANCE.toRolDto(savedRol);
    }

    @Override
    @Transactional(readOnly = true)
    public RolDto getRolById(Integer rolId) {
        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("Rol is not exist with given id: "+rolId));
        return RolMapperM.INSTANCE.toRolDto(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolDto> getAllRols() {
        List<Rol> rols = rolRepository.findAll();

        //return rols.stream().map((rol) -> RolMapper.mapToRolDto(rol))
        //        .collect(Collectors.toList());
        return rols.stream().map(RolMapperM.INSTANCE::toRolDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RolDto updateRol(Integer rodId, RolDto updatedRol) {
        Rol rol = rolRepository.findById(rodId).orElseThrow(
                () -> new ResourceNotFoundException("Rol is not exist with given id: "+rodId)
        );
        rol.setNombreRol(updatedRol.getNombreRol());

        Rol updatedRolObj = rolRepository.save(rol);
        return RolMapperM.INSTANCE.toRolDto(updatedRolObj);
    }

    @Override
    @Transactional
    public void deleteRol(Integer rolId) {
        Rol rol = rolRepository.findById(rolId).orElseThrow(
                () -> new ResourceNotFoundException("Rol is not exist with given id: "+rolId)
        );
        rolRepository.deleteById(rolId);
    }
}