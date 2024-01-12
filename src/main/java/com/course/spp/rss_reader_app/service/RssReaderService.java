package com.course.spp.rss_reader_app.service;

import com.course.spp.rss_reader_app.dto.FeedItemDto;
import com.course.spp.rss_reader_app.domain.Source;

import java.util.List;

public interface RssReaderService {

    List<FeedItemDto> parseFeedsFromSources(List<Source> sources,
                                            String category,
                                            int startPosition, int pageSize);
}
