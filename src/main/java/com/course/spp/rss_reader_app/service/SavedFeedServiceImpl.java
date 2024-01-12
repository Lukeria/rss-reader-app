package com.course.spp.rss_reader_app.service;

import com.course.spp.rss_reader_app.dto.FeedItemDto;
import com.course.spp.rss_reader_app.domain.SavedFeed;
import com.course.spp.rss_reader_app.domain.User;
import com.course.spp.rss_reader_app.dto.SavedFeedDto;
import com.course.spp.rss_reader_app.repository.SavedFeedRepository;
import com.course.spp.rss_reader_app.service.context.UserContext;
import com.course.spp.rss_reader_app.service.mapper.SavedFeedMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SavedFeedServiceImpl implements SavedFeedService {

    private final SavedFeedRepository savedFeedRepository;
    private final UserContext userContext;
    private final SavedFeedMapper savedFeedMapper;

    private final Integer PAGE_SIZE = 10;

    public SavedFeedServiceImpl(SavedFeedRepository savedFeedRepository, UserContext userContext, SavedFeedMapper savedFeedMapper) {
        this.savedFeedRepository = savedFeedRepository;
        this.userContext = userContext;
        this.savedFeedMapper = savedFeedMapper;
    }

    @Override
    public List<SavedFeedDto> loadSavedFeedList(Integer page) {
        Optional<User> userOptional = userContext.getAuthenticatedUser();

        int startPosition = (page - 1) * PAGE_SIZE;

        Pageable pageRequest = PageRequest.of(startPosition, PAGE_SIZE);

        return savedFeedRepository.findByUser(userOptional
                                .orElseThrow(() -> new RuntimeException("Not authenticated")),
                        pageRequest)
                .stream()
                .map(savedFeed -> savedFeedMapper.mapToDto(savedFeed))
                .collect(Collectors.toList());
    }

    @Override
    public void saveFeed(List<FeedItemDto> feedItemDtoList, int index) {
        Optional<User> userOptional = userContext.getAuthenticatedUser();

        FeedItemDto feedItemDto = feedItemDtoList.get(index);
        feedItemDto.setSaved(true);

        SavedFeed savedFeed = new SavedFeed();
        savedFeed.setUser(userOptional
                .orElseThrow(() -> new RuntimeException("Not authenticated")));
        savedFeed.setTitle(feedItemDto.getTitle());
        savedFeed.setLink(feedItemDto.getLink());
        savedFeed.setSource(feedItemDto.getSource());
        savedFeed.setPublishDate(feedItemDto.getPublishDate());
        savedFeed.setAuthor(feedItemDto.getAuthor());
        savedFeed.setCategory(feedItemDto.getCategories() != null ?
                feedItemDto.getCategories().stream().findAny().orElse(null) :
                null);

        savedFeedRepository.save(savedFeed);
    }

    @Override
    public long savedFeedsCount() {
        Optional<User> userOptional = userContext.getAuthenticatedUser();
        return userOptional.map(savedFeedRepository::countByUser).orElse(0L);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<User> userOptional = userContext.getAuthenticatedUser();
        savedFeedRepository.deleteByIdAndUser(id, userOptional
                .orElseThrow(() -> new RuntimeException("Not authenticated")));
    }
}
