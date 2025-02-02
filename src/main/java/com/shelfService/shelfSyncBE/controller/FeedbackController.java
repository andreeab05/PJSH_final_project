package com.shelfService.shelfSyncBE.controller;
import com.shelfService.shelfSyncBE.entity.Feedback;
import com.shelfService.shelfSyncBE.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/getFeedbackById")
    public ResponseEntity<Feedback> getFeedbackByUid(@RequestParam Integer feedback_id) {
        Feedback feedback = feedbackService.getFeedbackById(feedback_id);
        if (feedback != null)
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllFeedback")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> returnFeedbackDTOS = feedbackService.getAllFeedback();
        return new ResponseEntity<>(returnFeedbackDTOS,HttpStatus.OK);
    }

    @PostMapping("/createFeedback/{uid}")
    public ResponseEntity<String> createFeedback(@PathVariable Integer uid, @RequestBody Feedback feedbackRequest) {
        feedbackService.createFeedback(uid, feedbackRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteFeedbackById")
    public ResponseEntity<Void> deleteFeedbackById(@RequestParam Integer feedback_id) {
        feedbackService.deleteFeedbackById(feedback_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
