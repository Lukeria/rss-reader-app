package com.course.spp.rss_reader_app.repository;

import com.course.spp.rss_reader_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getByUsername(String username);
}
