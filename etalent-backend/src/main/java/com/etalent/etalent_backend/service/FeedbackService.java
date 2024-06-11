package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    FeedbackDto createFeedback(FeedbackDto feedbackDto);
    FeedbackDto getFeedbackById(Integer feedbackId);
    List<FeedbackDto> getAllFeedbacks();
    FeedbackDto updateFeedback(Integer feedbackId ,FeedbackDto updatedFeedback);
    void deleteFeedback(Integer feedbackId);
}
