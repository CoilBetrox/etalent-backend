package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.FeedbackDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.Feedback;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.FeedbackMapperM;
import com.etalent.etalent_backend.repository.AdminRepository;
import com.etalent.etalent_backend.repository.FeedbackRepository;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private UsuarioRepository usuarioRepository;
    private AdminRepository adminRepository;

    @Override
    @Transactional
    public FeedbackDto createFeedback(FeedbackDto feedbackDto) {
        if (feedbackDto.getIdUsuario() == null || feedbackDto.getIdAdmin() == null) {
            throw new InputMismatchException("idUsuario o idAdmin no deben ser null");
        }

        Feedback feedback = FeedbackMapperM.INSTANCE.toFeedback(feedbackDto);
        feedback.setUsuario(usuarioRepository.findById(feedbackDto.getIdUsuario()).orElseThrow(
                () -> new ResourceNotFoundException("Usuario no encontrado"+ feedbackDto.getIdUsuario())
        ));
        feedback.setAdmin(adminRepository.findById(feedbackDto.getIdAdmin()).orElseThrow(
                () -> new ResourceNotFoundException("Admin no encontrado"+ feedbackDto.getIdAdmin())
        ));

        feedback = feedbackRepository.save(feedback);
        return FeedbackMapperM.INSTANCE.toFeedbackDto(feedback);
        /*
        Feedback feedback = FeedbackMapperM.INSTANCE.toFeedback(feedbackDto);
        Usuario usuario = usuarioRepository.findById(feedbackDto.getIdUsuario())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Usuario not found with id: " + feedbackDto.getIdUsuario())
                );
        feedback.setUsuario(usuario);

        Admin admin = adminRepository.findById(feedbackDto.getIdAdmin())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Admin not found with id: " + feedbackDto.getIdAdmin())
                );
        feedback.setAdmin(admin);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        return FeedbackMapperM.INSTANCE.toFeedbackDto(savedFeedback);

         */
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
