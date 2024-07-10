package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.ComentarioFeedbackDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.ComentarioFeedback;
import com.etalent.etalent_backend.entity.Feedback;
import com.etalent.etalent_backend.entity.Usuario;
import com.etalent.etalent_backend.exceptions.ResourceNotFoundException;
import com.etalent.etalent_backend.mapper.ComentarioFeedbackMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.repository.ComentarioFeedbackRepository;
import com.etalent.etalent_backend.repository.FeedbackRepository;
import com.etalent.etalent_backend.repository.UsuarioRepository;
import com.etalent.etalent_backend.service.ComentarioFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ComentarioFeedbackServiceImpl implements ComentarioFeedbackService {

    private ComentarioFeedbackRepository comentarioFeedbackRepository;
    private AdminRegisterRepository adminRegisterRepository;
    private UsuarioRepository usuarioRepository;
    private FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public ComentarioFeedbackDto createComentarioFeedback(ComentarioFeedbackDto comentarioFeedbackDto, Integer idFeedback) {
        Feedback feedback = feedbackRepository.findById(idFeedback)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback no encontrado"));

        Admin admin = adminRegisterRepository.findByCorreoAdmin(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        Usuario usuario = null;
        if (comentarioFeedbackDto.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(comentarioFeedbackDto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }

        ComentarioFeedback comentarioFeedback = ComentarioFeedbackMapperM.INSTANCE.toComentarioFeedback(comentarioFeedbackDto);
        comentarioFeedback.setFeedback(feedback);
        comentarioFeedback.setUsuario(usuario);
        comentarioFeedback.setAdmin(admin);
        comentarioFeedback.setContenido(comentarioFeedbackDto.getContenido());
        comentarioFeedback.setFechaComentario(new Date());

        ComentarioFeedback savedComentarioFeedback = comentarioFeedbackRepository.save(comentarioFeedback);
        return ComentarioFeedbackMapperM.INSTANCE.toComentarioFeedbackDto(savedComentarioFeedback);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioFeedbackDto> getAllComentarioFeedback() {
        return comentarioFeedbackRepository.findAll().stream()
                .map(ComentarioFeedbackMapperM.INSTANCE::toComentarioFeedbackDto)
                .collect(Collectors.toList());
    }
}
