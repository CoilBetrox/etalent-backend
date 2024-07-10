package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}