package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findAllByAdmin(Admin admin);
}
