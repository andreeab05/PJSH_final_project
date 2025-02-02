package com.shelfService.shelfSyncBE.service;

import com.shelfService.shelfSyncBE.entity.Feedback;
import com.shelfService.shelfSyncBE.entity.User;
import com.shelfService.shelfSyncBE.repository.FeedbackRepository;
import com.shelfService.shelfSyncBE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    public Feedback getFeedbackById(Integer feedbackId) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(feedbackId);
        return optionalFeedback.orElse(null);
    }

    public List<Feedback> getAllFeedback() {
        List<Feedback> returnFeedbacks = feedbackRepository.findAll();
        return returnFeedbacks;
    }

    public String createFeedback(Integer userId, Feedback feedbackRequest) {
        User user = userRepository.findByUid(userId);
        if(user == null)
            return "Could not find user";
        Feedback feedback = new Feedback(
                user,
                feedbackRequest.getNew_feature(),
                feedbackRequest.getDegree_imp(),
                feedbackRequest.getProblems_pw(),
                feedbackRequest.getIssue(),
                feedbackRequest.getIssue_type1(),
                feedbackRequest.getIssue_type2(),
                feedbackRequest.getIssue_type3());
        feedbackRepository.save(feedback);
        return new String("Feedback created");
    }

    public void deleteFeedbackById(Integer feedbackId) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(feedbackId);
        feedbackRepository.deleteById(feedbackId);
    }
}
