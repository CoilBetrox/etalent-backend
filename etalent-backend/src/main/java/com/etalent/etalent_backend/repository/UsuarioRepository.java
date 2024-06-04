package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
