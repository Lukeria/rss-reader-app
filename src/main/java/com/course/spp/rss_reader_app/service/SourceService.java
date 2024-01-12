package com.course.spp.rss_reader_app.service;

import com.course.spp.rss_reader_app.domain.Source;

import java.io.IOException;
import java.util.List;

public interface SourceService {

    List<Source> getEnabledSources();
    void save(String name) throws IOException;
    List<Source> getSources();
    void deleteById(Integer id);
    void enableSources(List<Integer> ids);
}
