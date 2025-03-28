package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    FeedbackDto createFeedback(FeedbackDto feedbackDto, Integer idUsuario);
    FeedbackDto getFeedbackById(Integer feedbackId);
    List<FeedbackDto> getAllFeedbacks();
    List<FeedbackDto> getAllFeedbacksByAdminTienda();
}