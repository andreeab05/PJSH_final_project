package com.shelfService.shelfSyncBE.repository;

import com.shelfService.shelfSyncBE.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUid(Integer uid);

    @Transactional
    void deleteByUid(Integer uid);
}
