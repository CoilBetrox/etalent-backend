package com.etalent.etalent_backend.service.impl;

import com.etalent.etalent_backend.dto.ComentarioFeedbackDto;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.entity.ComentarioFeedback;
import com.etalent.etalent_backend.mapper.ComentarioFeedbackMapperM;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import com.etalent.etalent_backend.repository.ComentarioFeedbackRepository;
import com.etalent.etalent_backend.service.ComentarioFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ComentarioFeedbackServiceImpl implements ComentarioFeedbackService {

    private ComentarioFeedbackRepository comentarioFeedbackRepository;
    private AdminRegisterRepository adminRegisterRepository;

    @Override
    @Transactional
    public ComentarioFeedbackDto createComentarioFeedback(ComentarioFeedbackDto comentarioFeedbackDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoAdmin = authentication.getName();
        Admin admin = adminRegisterRepository.findByCorreoAdmin(correoAdmin)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        ComentarioFeedback comentarioFeedback = ComentarioFeedbackMapperM.INSTANCE.toComentarioFeedback(comentarioFeedbackDto);
        comentarioFeedback.setAdmin(admin);
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
