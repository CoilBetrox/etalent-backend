package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.RolUsuarioDto;
import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.RolUsuario;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.RolUsuarioMapperM;
import com.etalent.etalent_backend.mapper.UsuarioMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.repository.AdminRepository;
import com.etalent.etalent_backend.repository.RolUsuarioRepository;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private AdminRegisterRepository adminRegisterRepository;
    private RolUsuarioRepository rolUsuarioRepository;

    @Override
    @Transactional
    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        //captura el admin autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoAdmin = authentication.getName();
        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));
        System.out.println("----");
        System.out.println(admin.getCorreoAdmin());
        System.out.println(admin.getIdAdmin());
        System.out.println("----");
        Usuario usuario = UsuarioMapperM.INSTANCE.toUsuario(usuarioDto);
        Integer idRol = 5;
        RolUsuario rolUsuario = rolUsuarioRepository.findById(idRol)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        System.out.println("----");
        System.out.println(rolUsuario.getNombreRolUsuario());
        System.out.println(rolUsuario.getIdRolUsuario());
        System.out.println("----");
        usuario.setRolUsuario(rolUsuario);

        usuario.setAdmin(admin);
        Usuario saveUsuario = usuarioRepository.save(usuario);
        System.out.println("----");
        System.out.println(usuario.getAdmin());
        System.out.println(usuario.getRolUsuario());
        System.out.println("----");
        return UsuarioMapperM.INSTANCE.toUsuarioDto(saveUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getUsuarioById(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario is not exist with given id: "+usuarioId));
        return UsuarioMapperM.INSTANCE.toUsuarioDto(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> getAllUsuarios() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoAdmin = authentication.getName();
        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(()-> new RuntimeException("Admin no encontrado"));

        List<Usuario> usuarios = usuarioRepository.findAllByAdmin(admin);
        return usuarios.stream().map(UsuarioMapperM.INSTANCE::toUsuarioDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsuarioDto updateUsuario(Integer usuarioId, UsuarioDto updatedUsuario) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoAdmin = authentication.getName();
        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(()-> new RuntimeException("Admin no encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario is not exist with given id: "+usuarioId));

        if (!usuario.getAdmin().getIdAdmin().equals(admin.getIdAdmin())) {
            throw new RuntimeException("Usuario no está asociado al admin autenticado");
        }

        if (updatedUsuario.getRolUsuario() != null && updatedUsuario.getRolUsuario().getIdRolUsuario() != null){
            //RolUsuario rolUsuario = rolUsuarioRepository.findById(updatedUsuario.getRolUsuario().getIdRolUsuario())
            //                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setRolUsuario(rolUsuarioRepository.findById(updatedUsuario.getRolUsuario().getIdRolUsuario())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado")));
            /*
            usuario.setRolUsuario(rolUsuarioRepository.findById(updatedUsuario.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado")));
             */
        }

        Usuario updatedUsuarioObj = usuarioRepository.save(usuario);
        return UsuarioMapperM.INSTANCE.toUsuarioDto(updatedUsuarioObj);
    }

    @Override
    @Transactional
    public void deleteUsuario(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new ResourceNotFoundException("Usuario is not exist with given id: "+usuarioId)
        );
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public UsuarioDto addRolUsuario(Integer usuarioId, Integer rolUsuarioId) {
        return null;
    }
}
