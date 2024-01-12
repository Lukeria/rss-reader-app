package com.course.spp.rss_reader_app.service.mapper;

import com.course.spp.rss_reader_app.domain.SavedFeed;
import com.course.spp.rss_reader_app.dto.SavedFeedDto;
import org.springframework.stereotype.Component;

@Component
public class SavedFeedMapper {

    public SavedFeedDto mapToDto(SavedFeed savedFeed){
        SavedFeedDto dto = new SavedFeedDto();
        dto.setId(savedFeed.getId());
        dto.setCategory(savedFeed.getCategory());
        dto.setAuthor(savedFeed.getAuthor());
        dto.setLink(savedFeed.getLink());
        dto.setSource(savedFeed.getSource());
        dto.setTitle(savedFeed.getTitle());
        dto.setPublishDate(savedFeed.getPublishDate());
        return dto;
    }
}
