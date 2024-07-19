package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByAdmin(Admin admin);

    @Query("SELECT f FROM Feedback f")
    List<Feedback> findByTipoAdmin();
}
