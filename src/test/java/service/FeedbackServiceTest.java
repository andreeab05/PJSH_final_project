package service;

import com.shelfService.shelfSyncBE.entity.Feedback;
import com.shelfService.shelfSyncBE.entity.Reader;
import com.shelfService.shelfSyncBE.entity.User;
import com.shelfService.shelfSyncBE.repository.FeedbackRepository;
import com.shelfService.shelfSyncBE.repository.UserRepository;
import com.shelfService.shelfSyncBE.service.FeedbackService;
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
class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    private Feedback feedback;
    private User user;

    @BeforeEach
    void setUp() {
        user = new Reader();
        user.setUid(1);

        feedback = new Feedback(user, "New Feature", 5, true, "Issue",
                true, false, false);
        feedback.setFeedbackId(1);
    }

    @Test
    void testGetFeedbackById_Found() {
        when(feedbackRepository.findById(1)).thenReturn(Optional.of(feedback));

        Feedback result = feedbackService.getFeedbackById(1);

        assertNotNull(result);
        assertEquals(1, result.getFeedbackId());
        verify(feedbackRepository, times(1)).findById(1);
    }

    @Test
    void testGetFeedbackById_NotFound() {
        when(feedbackRepository.findById(2)).thenReturn(Optional.empty());

        Feedback result = feedbackService.getFeedbackById(2);

        assertNull(result);
        verify(feedbackRepository, times(1)).findById(2);
    }

    @Test
    void testGetAllFeedback() {
        Feedback feedback2 = new Feedback(user, "Another feature", 3, true, "Issue2",
                true, false, false);
        List<Feedback> mockFeedbacks = Arrays.asList(feedback, feedback2);
        when(feedbackRepository.findAll()).thenReturn(mockFeedbacks);

        List<Feedback> result = feedbackService.getAllFeedback();

        assertEquals(2, result.size());
        verify(feedbackRepository, times(1)).findAll();
    }

    @Test
    void testCreateFeedback_UserExists() {
        when(userRepository.findByUid(1)).thenReturn(user);
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        String result = feedbackService.createFeedback(1, feedback);

        assertEquals("Feedback created", result);
        verify(userRepository, times(1)).findByUid(1);
        verify(feedbackRepository, times(1)).save(any(Feedback.class));
    }

    @Test
    void testCreateFeedback_UserNotFound() {
        when(userRepository.findByUid(2)).thenReturn(null);

        String result = feedbackService.createFeedback(2, feedback);

        assertEquals("Could not find user", result);
        verify(userRepository, times(1)).findByUid(2);
        verify(feedbackRepository, never()).save(any(Feedback.class));
    }

    @Test
    void testDeleteFeedbackById() {
        when(feedbackRepository.findById(1)).thenReturn(Optional.of(feedback));
        doNothing().when(feedbackRepository).deleteById(1);

        feedbackService.deleteFeedbackById(1);

        verify(feedbackRepository, times(1)).deleteById(1);
    }
}
