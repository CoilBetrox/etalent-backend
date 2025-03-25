package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.CursoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, Integer> {

    @Query("SELECT c FROM CursoUsuario c JOIN c.usuario u WHERE u.idUsuario = :usuarioId")
    List<CursoUsuario> findCursosByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
