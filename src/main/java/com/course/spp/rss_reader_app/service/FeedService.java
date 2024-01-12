package com.course.spp.rss_reader_app.service;

import com.course.spp.rss_reader_app.dto.FeedItemDto;

import java.util.List;

public interface FeedService {

    List<FeedItemDto> loadFeedList(Integer page);
    List<FeedItemDto> loadFilteredFeedList(String filter, Integer page);
}
