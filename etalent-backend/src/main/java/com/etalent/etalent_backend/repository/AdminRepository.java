package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    List<Admin> findByRolAdminsNombreRol(String nombreRol);

    @Query("SELECT u FROM Usuario u WHERE u.admin.idAdmin = :adminId")
    List<Usuario> findUsuariosByAdminId(@Param("adminId") Integer adminId);
}