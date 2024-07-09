package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.ComentarioFeedbackDto;
import com.etalent.etalent_backend.service.ComentarioFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/comentariosFeedback")
public class ComentarioFeedbackController {
    private ComentarioFeedbackService comentarioFeedbackService;

    @PostMapping("/{idFeedback}")
    public ResponseEntity<ComentarioFeedbackDto> createComentarioFeedback(@RequestBody ComentarioFeedbackDto comentarioFeedbackDto,
                                                                          @PathVariable Integer idFeedback) {
        ComentarioFeedbackDto createdComentarioFeedback = comentarioFeedbackService
                .createComentarioFeedback(comentarioFeedbackDto,
                        idFeedback);
        return ResponseEntity.ok(createdComentarioFeedback);
    }

    @GetMapping
    public ResponseEntity<List<ComentarioFeedbackDto>> getAllComentarioFeedback() {
        List<ComentarioFeedbackDto> comentariosFeedback = comentarioFeedbackService.getAllComentarioFeedback();
        return ResponseEntity.ok(comentariosFeedback);
    }
}
