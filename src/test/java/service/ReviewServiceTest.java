package service;

import com.shelfService.shelfSyncBE.entity.*;
import com.shelfService.shelfSyncBE.repository.BookRepository;
import com.shelfService.shelfSyncBE.repository.ReviewRepository;
import com.shelfService.shelfSyncBE.repository.UserRepository;
import com.shelfService.shelfSyncBE.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review mockReview;
    private Reader mockReader;
    private Author mockAuthor;

    private Book mockBook;

    private Review review1;
    private Review review2;

    @BeforeEach
    void setUp(){
        mockReader = new Reader();
        mockReader.setUsername("testuser");
        mockReader.setDescription("Mock description");

        mockAuthor = new Author();
        mockAuthor.setUsername("testauthor");
        mockAuthor.setDescription("Mock author description");

        mockBook = new Book("Test title", "Test description", 100, "link", mockAuthor, new Category(1, "horror"), new Category(2, "fantasy"), new Category(3, "romance"));
        mockBook.setBookId(1);
        mockReview = new Review(5, "Review text", mockReader, mockBook);
        mockReview.setReviewId(1);

        review1 = new Review();
        review1.setUser(mockReader);
        review1.setReviewId(1);
        review1.setBook(mockBook);
        review1.setComment("Great book!");

        review2 = new Review();
        review2.setUser(mockReader);
        review2.setReviewId(2);
        review2.setBook(mockBook);
        review2.setComment("Not bad.");
    }

    @Test
    public void testGetReviewById_Found() throws Exception {
        when(reviewRepository.findById(1)).thenReturn(Optional.of(mockReview));
        Review result = reviewService.getReviewById(1);

        assertNotNull(result);
        assertEquals(1, result.getReviewId());
        verify(reviewRepository, times(1)).findById(1);
    }
    @Test
    void testGetReviewsByBookId_BookExists() throws Exception {
        when(bookRepository.findById(1)).thenReturn(Optional.of(mockBook));
        when(reviewRepository.findAllByBook(mockBook)).thenReturn(Arrays.asList(review1, review2));

        List<Review> result = reviewService.getReviewsByBookId(1);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Great book!", result.get(0).getComment());
        assertEquals("Not bad.", result.get(1).getComment());
        verify(bookRepository, times(1)).findById(1);
        verify(reviewRepository, times(1)).findAllByBook(mockBook);
    }

    @Test
    void testGetReviewsByBookId_BookNotFound() {
        when(bookRepository.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            reviewService.getReviewsByBookId(2);
        });

        assertEquals("{getReviewsByBookId} - Couldn't find book for book id 2", exception.getMessage());
        verify(bookRepository, times(1)).findById(2);
        verify(reviewRepository, never()).findAllByBook(any());
    }

    @Test
    void testCreateReview_Success() throws Exception {
        when(bookRepository.findById(1)).thenReturn(Optional.of(mockBook));
        when(userRepository.findByUid(1)).thenReturn(mockReader);
        when(reviewRepository.save(any(Review.class))).thenReturn(mockReview);

        Review result = reviewService.createReview(1, 1, mockReview);

        assertNotNull(result);
        assertEquals("Review text", result.getComment());
        assertEquals(5, result.getRating());

        verify(bookRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findByUid(1);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testCreateReview_BookNotFound() {
        when(bookRepository.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            reviewService.createReview(1, 2, mockReview);
        });

        assertEquals("{createReview} - Couldn't find book for book id 2", exception.getMessage());
        verify(bookRepository, times(1)).findById(2);
        verify(userRepository, never()).findByUid(anyInt());
        verify(reviewRepository, never()).save(any());
    }

    @Test
    void testCreateReview_UserNotFound() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(mockBook));
        when(userRepository.findByUid(3)).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {
            reviewService.createReview(3, 1, mockReview);
        });

        assertEquals("User not found", exception.getMessage());
        verify(bookRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findByUid(3);
        verify(reviewRepository, never()).save(any());
    }
    @Test
    void testUpdateReview_Success() throws Exception {
        Review updatedReview = new Review();
        updatedReview.setReviewId(1);
        updatedReview.setComment("Updated review");
        updatedReview.setRating(4);

        when(reviewRepository.findById(1)).thenReturn(Optional.of(mockReview));
        when(reviewRepository.save(any(Review.class))).thenReturn(updatedReview);

        Review result = reviewService.updateReview(updatedReview);

        assertNotNull(result);
        assertEquals("Updated review", result.getComment());
        assertEquals(4, result.getRating());

        verify(reviewRepository, times(1)).findById(1);
        verify(reviewRepository, times(1)).save(mockReview);
    }
}
