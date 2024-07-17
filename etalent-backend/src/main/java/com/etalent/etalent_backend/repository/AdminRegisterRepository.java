package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRegisterRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByCorreoAdmin(String correoAdmin);

}