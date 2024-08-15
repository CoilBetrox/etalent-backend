package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.ComentarioFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioFeedbackRepository extends JpaRepository<ComentarioFeedback, Integer> {

    @Query("SELECT c FROM ComentarioFeedback c JOIN c.feedback f WHERE f.idFeedback = :feedbackId")
    List<ComentarioFeedback> findByFeedbackId(@Param("feedbackId") Integer feedbackId);
}
