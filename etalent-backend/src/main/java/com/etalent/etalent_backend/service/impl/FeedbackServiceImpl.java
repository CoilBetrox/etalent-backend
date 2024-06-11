package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.FeedbackDto;
import com.etalent.etalent_backend.entity.Feedback;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.FeedbackMapperM;
import com.etalent.etalent_backend.repository.FeedbackRepository;
import com.etalent.etalent_backend.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public FeedbackDto createFeedback(FeedbackDto feedbackDto) {
        return FeedbackMapperM.INSTANCE.toFeedbackDto(
                feedbackRepository.save(FeedbackMapperM.INSTANCE.toFeedback(feedbackDto)));
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackDto getFeedbackById(Integer feedbackId) {
        return FeedbackMapperM.INSTANCE
                .toFeedbackDto(feedbackRepository.findById(feedbackId).orElseThrow(
                        () -> new ResourceNotFoundException("Feedback is not exist with given id: "+feedbackId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackRepository.findAll().stream().map(FeedbackMapperM.INSTANCE::toFeedbackDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FeedbackDto updateFeedback(Integer feedbackId, FeedbackDto updatedFeedback) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(
                () -> new ResourceNotFoundException("Feedback is not exist with given id: "+feedbackId));
        feedback.setTituloFeedback(updatedFeedback.getTituloFeedback());
        feedback.setDescripcionFeedback(updatedFeedback.getDescripcionFeedback());
        feedback.setDescripcionFeedback(updatedFeedback.getDescripcionFeedback());

        return FeedbackMapperM.INSTANCE.toFeedbackDto(feedbackRepository.save(feedback));
    }

    @Override
    @Transactional
    public void deleteFeedback(Integer feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(
                ()-> new ResourceNotFoundException("Feedback is not exist with given id: "+feedbackId)
        );
        feedbackRepository.deleteById(feedbackId);
    }
}
