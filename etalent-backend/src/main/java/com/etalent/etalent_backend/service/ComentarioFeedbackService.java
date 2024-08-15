package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.dto.ComentarioFeedbackDto;

import java.util.List;

public interface ComentarioFeedbackService {
    ComentarioFeedbackDto createComentarioFeedback(ComentarioFeedbackDto comentarioFeedbackDto, Integer IdFeedback);
    List<ComentarioFeedbackDto> getAllComentarioFeedback();
    List<ComentarioFeedbackDto> getComentariosByFeedbackId(Integer feedbackId);
}
