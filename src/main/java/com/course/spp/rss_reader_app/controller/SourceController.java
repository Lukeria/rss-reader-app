package com.course.spp.rss_reader_app.controller;

import com.course.spp.rss_reader_app.domain.Source;
import com.course.spp.rss_reader_app.service.SourceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/rss/sources")
public class SourceController {

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping
    public String showSources(Model model) {

        List<Source> sourceList = sourceService.getSources();
        model.addAttribute("sourceList", sourceList);

        return "sourceManager";
    }

    @GetMapping("/delete/{id}")
    public String deleteSources(@PathVariable(value = "id") Integer id) {

        sourceService.deleteById(id);
        return "redirect:/rss/sources";
    }

    @PostMapping
    public String addSource(@RequestParam("name") String name) throws IOException {

        sourceService.save(name);
        return "redirect:/rss/sources";
    }

    @PostMapping("/enable")
    public String enableSources(@RequestParam(value = "enabled_group", required = false)
                                List<Integer> enabledSources) {
        if (enabledSources != null) {
            sourceService.enableSources(enabledSources);
            return "redirect:/rss/feeds";
        }

        return "redirect:/rss/sources";
    }
}
