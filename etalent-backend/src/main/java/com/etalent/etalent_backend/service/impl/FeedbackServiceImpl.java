package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.FeedbackDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.Feedback;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.FeedbackMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.repository.FeedbackRepository;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private UsuarioRepository usuarioRepository;
    private AdminRegisterRepository adminRegisterRepository;

    @Override
    @Transactional
    public FeedbackDto createFeedback(FeedbackDto feedbackDto, Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Admin admin = adminRegisterRepository.findByCorreoAdmin(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Admin no encontrado"));

        Feedback feedback = FeedbackMapperM.INSTANCE.toFeedback(feedbackDto);
        feedback.setUsuario(usuario);
        feedback.setAdmin(admin);
        feedback.setFechaCreacionFeedback(new Date());

        feedback = feedbackRepository.save(feedback);
        return FeedbackMapperM.INSTANCE.toFeedbackDto(feedback);
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
        Admin admin = adminRegisterRepository.findByCorreoAdmin(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Admin no encontrado"));

        List<Feedback> feedbacks = feedbackRepository.findAllByAdmin(admin);
        return feedbacks.stream().map(FeedbackMapperM.INSTANCE::toFeedbackDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getAllFeedbacksByAdminTienda() {
        return feedbackRepository.findByTipoAdmin().stream()
                .map(FeedbackMapperM.INSTANCE::toFeedbackDto)
                .collect(Collectors.toList());
    }
}