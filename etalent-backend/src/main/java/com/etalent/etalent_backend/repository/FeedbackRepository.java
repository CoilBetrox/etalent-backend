package com.etalent.etalent_backend.repository;

import com.etalent.etalent_backend.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
