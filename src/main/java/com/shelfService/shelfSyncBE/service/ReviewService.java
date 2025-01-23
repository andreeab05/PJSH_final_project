package com.shelfService.shelfSyncBE.service;

import com.shelfService.shelfSyncBE.entity.Book;
import com.shelfService.shelfSyncBE.entity.Review;
import com.shelfService.shelfSyncBE.repository.BookRepository;
import com.shelfService.shelfSyncBE.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookRepository bookRepository;
    public Review getReviewById(Integer reviewId) throws Exception{
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent())
            throw new Exception("{getReviewById} - Couldn't find review");
        return review.get();
    }
//
//    public Response getReviewByUid(@RequestParam String uid, Integer bookId) {
//        User user = userRepository.findByUid(uid);
//        Optional <Book> book = bookRepository.findById(bookId);
//        if(user == null || book.isEmpty())
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        Review review = reviewRepository.findByUserAndBook(user,book.get());
//        if(review != null)
//            return new ResponseEntity<>(new ReturnReviewDTO(review), HttpStatus.OK);
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }

    public List<Review> getReviewsByBookId(Integer bookId) throws Exception{
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty())
            throw new Exception("{getReviewsByBookId} - Couldn't find book for book id " + bookId);
        List<Review> reviews = reviewRepository.findAllByBook(book.get());
        return reviews;
    }

    public Review createReview(Integer bookId, Review review) throws Exception{
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        //User optionalUser = userRepository.findByUid(addReviewDTO.getUid());

        if(optionalBook.isEmpty())
            throw new Exception("{createReview} - Couldn't find book for book id " + bookId);
//        if(optionalUser == null)
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

//        if(reviewRepository.findByUserAndBook(optionalUser,optionalBook.get())!=null)
//            return new ResponseEntity<>("Review already exists", HttpStatus.BAD_REQUEST);

        reviewRepository.save(review);
        return review;
    }
    public Review updateReview(Review updateReview) throws Exception{
        Optional<Review>  optionalReview = reviewRepository.findById(updateReview.getReviewId());
        if(optionalReview.isEmpty())
            throw new Exception("{updateReview} - Couldn't find review");

        Review review = optionalReview.get();

        // Set fields
        review.setRating(updateReview.getRating());
        review.setComment(updateReview.getComment());
//        review.setDate_modified(LocalDate.now());

        reviewRepository.save(review);
        return review;
    }

    public void deleteReviewById(Integer review_id){
        Optional<Review>  optionalReview = reviewRepository.findById(review_id);
        if(optionalReview.isEmpty())
            throw new RuntimeException("{deleteReviewById} - Couldn't find review");
        reviewRepository.deleteById(review_id);
    }
}
