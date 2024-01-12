package com.course.spp.rss_reader_app.service;

import com.course.spp.rss_reader_app.dto.FeedItemDto;
import com.course.spp.rss_reader_app.dto.SavedFeedDto;

import java.util.List;

public interface SavedFeedService {

    List<SavedFeedDto> loadSavedFeedList(Integer page);
    void saveFeed(List<FeedItemDto> feedItemDtoList, int index);
    long savedFeedsCount();
    void deleteById(Integer id);
}
