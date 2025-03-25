package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
