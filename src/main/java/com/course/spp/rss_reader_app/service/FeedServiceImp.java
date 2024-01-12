package com.course.spp.rss_reader_app.service;

import com.course.spp.rss_reader_app.dto.FeedItemDto;
import com.course.spp.rss_reader_app.domain.Source;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImp implements FeedService {

    private final RssReaderService rssReaderService;
    private final SourceService sourceService;
    private final Integer PAGE_SIZE = 10;

    public FeedServiceImp(RssReaderService rssReaderService,
                          SourceService sourceService) {
        this.rssReaderService = rssReaderService;
        this.sourceService = sourceService;
    }

    @Override
    public List<FeedItemDto> loadFeedList(Integer page) {
        int startPosition = (page - 1) * PAGE_SIZE;
        List<Source> sources = sourceService.getEnabledSources();

        return rssReaderService.parseFeedsFromSources(sources, null, startPosition, PAGE_SIZE);
    }

    @Override
    public List<FeedItemDto> loadFilteredFeedList(String filter, Integer page) {
        int startPosition = (page - 1) * PAGE_SIZE;
        List<Source> sources = sourceService.getEnabledSources();

        return rssReaderService.parseFeedsFromSources(sources, filter, startPosition, PAGE_SIZE);
    }
}
