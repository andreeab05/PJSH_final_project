package com.shelfService.shelfSyncBE.controller;

import com.shelfService.shelfSyncBE.entity.Review;
import com.shelfService.shelfSyncBE.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Get review by ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer reviewId) {
        try {
            Review review = reviewService.getReviewById(reviewId);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get reviews by book ID
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable Integer bookId) {
        try {
            List<Review> reviews = reviewService.getReviewsByBookId(bookId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Create a new review
    @PostMapping("/book/{bookId}")
    public ResponseEntity<Review> createReview(@PathVariable Integer bookId, @RequestParam Integer userId, @RequestBody Review review) {
        try {
            Review createdReview = reviewService.createReview(userId, bookId, review);
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing review
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer reviewId, @RequestBody Review updateReview) {
        try {
            updateReview.setReviewId(reviewId); // Set the reviewId of the updated review
            Review updatedReview = reviewService.updateReview(updateReview);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a review by ID
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Integer reviewId) {
        try {
            reviewService.deleteReviewById(reviewId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
