package com.course.spp.rss_reader_app.controller;

import com.course.spp.rss_reader_app.dto.FeedItemDto;
import com.course.spp.rss_reader_app.dto.SavedFeedDto;
import com.course.spp.rss_reader_app.service.SavedFeedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rss/saved-feeds")
@SessionAttributes(value = {"feedItems", "savedFeedsCount"})
public class SavedFeedController {

    private final SavedFeedService savedFeedService;

    public SavedFeedController(SavedFeedService savedFeedService) {
        this.savedFeedService = savedFeedService;
    }

    @GetMapping
    public String loadSavedFeedList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    Model model) {
        List<SavedFeedDto> savedFeedItems = savedFeedService.loadSavedFeedList(page);
        model.addAttribute("feedItems", savedFeedItems);
        model.addAttribute("page", page);

        return "savedFeeds";
    }

    @PostMapping
    public String addSavedFeed(@RequestParam(value = "index", defaultValue = "1") Integer index,
                               @ModelAttribute(name = "feedItems") List<FeedItemDto> feedItemDtos,
                               @ModelAttribute(name = "savedFeedsCount") Long savedFeedsCount,
                               Model model) {

        savedFeedService.saveFeed(feedItemDtos, index);
        model.addAttribute("savedFeedsCount", savedFeedsCount + 1);
        model.addAttribute("feedItems", feedItemDtos);

        return "feeds";
    }

    @GetMapping("/delete/{id}")
    public String deleteSavedFeed(@PathVariable(value = "id") Integer id,
                                  @ModelAttribute(name = "savedFeedsCount") Long savedFeedsCount,
                                  Model model) {
        savedFeedService.deleteById(id);
        model.addAttribute("savedFeedsCount", savedFeedsCount - 1);

        return "redirect:/rss/saved-feeds";
    }

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("savedFeedsCount", savedFeedService.savedFeedsCount());
    }
}
