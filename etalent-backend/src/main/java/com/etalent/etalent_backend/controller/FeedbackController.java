package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.FeedbackDto;
import com.etalent.etalent_backend.entity.Feedback;
import com.etalent.etalent_backend.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private FeedbackService feedbackService;

    //Build Post Feedback REST API
    @PostMapping("/{idUsuario}")
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackDto feedbackDto,
                                                      @PathVariable Integer idUsuario){
        return new ResponseEntity<>(feedbackService.createFeedback(feedbackDto, idUsuario), HttpStatus.CREATED);
    }

    //Build Get Feedback By Id REST API
    @GetMapping("{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable("id") Integer feedbackId) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(feedbackId));
    }

    //Build Get All Feedback REST API
    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    //Build Update Feeback REST API
    @PutMapping("{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable("id") Integer feedbackId,
                                                      @RequestBody FeedbackDto updatedFeedbackDto) {
        return ResponseEntity.ok(feedbackService.updateFeedback(feedbackId, updatedFeedbackDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable("id") Integer feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.ok("Feedback deleted succesfully");
    }
}
