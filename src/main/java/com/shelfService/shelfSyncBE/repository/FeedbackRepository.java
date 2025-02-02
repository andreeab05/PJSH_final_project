package com.shelfService.shelfSyncBE.repository;

import com.shelfService.shelfSyncBE.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}

