package com.course.spp.rss_reader_app.repository;

import com.course.spp.rss_reader_app.domain.SavedFeed;
import com.course.spp.rss_reader_app.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedFeedRepository extends JpaRepository<SavedFeed, Integer> {

    Page<SavedFeed> findByUser(User user, Pageable pageable);

    long countByUser(User user);

    void deleteByIdAndUser(Integer id, User user);
}
