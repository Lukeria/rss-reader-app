package com.course.spp.rss_reader_app.controller;

import com.course.spp.rss_reader_app.dto.FeedItemDto;
import com.course.spp.rss_reader_app.service.FeedService;
import com.course.spp.rss_reader_app.service.SavedFeedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rss/feeds")
@SessionAttributes(value = {"feedItems", "savedFeedsCount"})
public class FeedController {

    private final FeedService feedService;
    private final SavedFeedService savedFeedService;

    public FeedController(FeedService feedService, SavedFeedService savedFeedService) {
        this.feedService = feedService;
        this.savedFeedService = savedFeedService;
    }

    @GetMapping
    public String loadFeedList(@RequestParam(value = "filter", required = false) String filter,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               Model model) {

        List<FeedItemDto> feedItemDtos;
        if (filter == null || filter.isEmpty()) {
            feedItemDtos = feedService.loadFeedList(page);
        } else {
            feedItemDtos = feedService.loadFilteredFeedList(filter, page);
        }

        model.addAttribute("feedItems", feedItemDtos);
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);

        return "feeds";
    }

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("savedFeedsCount", savedFeedService.savedFeedsCount());
    }
}
