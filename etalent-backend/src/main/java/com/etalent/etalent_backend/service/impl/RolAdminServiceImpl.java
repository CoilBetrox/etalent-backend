package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.RolAdminDto;
import com.etalent.etalent_backend.entity.RolAdmin;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.RolAdminMapperM;
import com.etalent.etalent_backend.repository.RolAdminRepository;
import com.etalent.etalent_backend.service.RolAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RolAdminServiceImpl implements RolAdminService {

    private RolAdminRepository rolAdminRepository;

    @Override
    @Transactional
    public RolAdminDto createRol(RolAdminDto rolAdminDto) {
        //Rol rol = RolMapper.mapToRol(rolDto);
        RolAdmin rol = RolAdminMapperM.INSTANCE.toRol(rolAdminDto);
        RolAdmin savedRol = rolAdminRepository.save(rol);
        return RolAdminMapperM.INSTANCE.toRolDto(savedRol);
    }

    @Override
    @Transactional(readOnly = true)
    public RolAdminDto getRolById(Integer rolId) {
        RolAdmin rolAdmin = rolAdminRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("Rol is not exist with given id: "+rolId));
        return RolAdminMapperM.INSTANCE.toRolDto(rolAdmin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolAdminDto> getAllRols() {
        List<RolAdmin> rols = rolAdminRepository.findAll();

        //return rols.stream().map((rol) -> RolMapper.mapToRolDto(rol))
        //        .collect(Collectors.toList());
        return rols.stream().map(RolAdminMapperM.INSTANCE::toRolDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RolAdminDto updateRol(Integer rodId, RolAdminDto updatedRol) {
        RolAdmin rolAdmin = rolAdminRepository.findById(rodId).orElseThrow(
                () -> new ResourceNotFoundException("Rol is not exist with given id: "+rodId)
        );
        rolAdmin.setNombreRol(updatedRol.getNombreRol());

        RolAdmin updatedRolObj = rolAdminRepository.save(rolAdmin);
        return RolAdminMapperM.INSTANCE.toRolDto(updatedRolObj);
    }

    @Override
    @Transactional
    public void deleteRol(Integer rolId) {
        RolAdmin rolAdmin = rolAdminRepository.findById(rolId).orElseThrow(
                () -> new ResourceNotFoundException("Rol is not exist with given id: "+rolId)
        );
        rolAdminRepository.deleteById(rolId);
    }
}