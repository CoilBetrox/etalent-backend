package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.ComentarioFeedbackDto;
import com.etalent.etalent_backend.service.ComentarioFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios-feedback")
@AllArgsConstructor
public class ComentarioFeedbackController {
    private ComentarioFeedbackService comentarioFeedbackService;

    @PostMapping
    public ResponseEntity<ComentarioFeedbackDto> createComentarioFeedback(@RequestBody ComentarioFeedbackDto comentarioFeedbackDto) {
        ComentarioFeedbackDto createdComentarioFeedback = comentarioFeedbackService.createComentarioFeedback(comentarioFeedbackDto);
        return ResponseEntity.ok(createdComentarioFeedback);
    }

    @GetMapping
    public ResponseEntity<List<ComentarioFeedbackDto>> getAllComentarioFeedback() {
        List<ComentarioFeedbackDto> comentariosFeedback = comentarioFeedbackService.getAllComentarioFeedback();
        return ResponseEntity.ok(comentariosFeedback);
    }
}
