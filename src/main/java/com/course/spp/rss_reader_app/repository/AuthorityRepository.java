package com.course.spp.rss_reader_app.repository;

import com.course.spp.rss_reader_app.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority getByName(String name);
}
