package com.course.spp.rss_reader_app.repository;

import com.course.spp.rss_reader_app.domain.Source;
import com.course.spp.rss_reader_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Integer> {

    @Modifying
    @Query(value = "UPDATE `sources` SET `sources`.enabled = 1 WHERE `sources`.id IN (:ids)" +
            " AND (`sources`.user_id = :userId OR `sources`.user_id = NULL)", nativeQuery = true)
    void enableSources(@Param("ids") List<Integer> ids, @Param("userId") Integer userId);

    @Modifying
    @Query(value = "UPDATE `sources` SET `sources`.enabled = 0 WHERE `sources`.id NOT IN (:ids) " +
            " AND (`sources`.user_id = :userId OR `sources`.user_id = NULL)", nativeQuery = true)
    void disableOtherSources(@Param("ids") List<Integer> ids, @Param("userId") Integer userId);

    @Modifying
    @Query(value = "UPDATE `sources` SET `sources`.enabled = 1 WHERE `sources`.id IN (:ids)" +
            " AND `sources`.default_value = 1", nativeQuery = true)
    void enableSources(@Param("ids") List<Integer> ids);

    @Modifying
    @Query(value = "UPDATE `sources` SET `sources`.enabled = 0 WHERE `sources`.id NOT IN (:ids) " +
            " AND `sources`.default_value = 1", nativeQuery = true)
    void disableOtherSources(@Param("ids") List<Integer> ids);

    List<Source> findAllByEnabledAndDefaultValue(boolean enabled, boolean defaultValue);

    List<Source> findAllByDefaultValue(boolean defaultValue);

    void deleteByIdAndUser(Integer id, User user);

    List<Source> findByUserOrDefaultValue(User user, boolean defaultValue);

    @Query(value = "SELECT * FROM `sources` WHERE `sources`.enabled = :enabled " +
            "AND (`sources`.user_id = :userId OR `sources`.user_id IS NULL)", nativeQuery = true)
    List<Source> findByEnabledAndUser(@Param("enabled") boolean enabled, @Param("userId") Integer userId);

}
