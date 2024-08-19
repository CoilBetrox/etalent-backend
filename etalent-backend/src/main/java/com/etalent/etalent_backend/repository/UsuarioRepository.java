package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.dto.UsuarioDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findAllByAdmin(Admin admin);

    @Modifying
    @Transactional
    @Query("DELETE FROM ComentarioFeedback cf WHERE cf.feedback.idFeedback IN (SELECT f.idFeedback FROM Feedback f WHERE f.usuario.idUsuario = :usuarioId)")
    void deleteComentariosByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Feedback f WHERE f.usuario.idUsuario = :usuarioId")
    void deleteFeedbacksByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Usuario u WHERE u.idUsuario = :usuarioId")
    void deleteUsuarioById(@Param("usuarioId") Integer usuarioId);

    List<Usuario> findByAdmin(Admin admin);
    List<Usuario> findBySapUsuario(String sapUsuario);
}
