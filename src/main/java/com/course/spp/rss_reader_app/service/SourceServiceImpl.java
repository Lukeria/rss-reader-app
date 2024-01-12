package com.course.spp.rss_reader_app.service;

import com.course.spp.rss_reader_app.domain.Source;
import com.course.spp.rss_reader_app.domain.User;
import com.course.spp.rss_reader_app.repository.SourceRepository;
import com.course.spp.rss_reader_app.service.context.UserContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;
    private final UserContext userContext;

    public SourceServiceImpl(SourceRepository sourceRepository, UserContext userContext) {
        this.sourceRepository = sourceRepository;
        this.userContext = userContext;
    }

    @Override
    public List<Source> getEnabledSources() {
        Optional<User> userOptional = userContext.getAuthenticatedUser();

        if (userOptional.isPresent()) {
            return sourceRepository.findByEnabledAndUser(true, userOptional.get().getId());
        }

        return sourceRepository.findAllByEnabledAndDefaultValue(true, true);
    }

    @Override
    public void save(String name) throws IOException {
        URL url = new URL(name);
        InputStream inputStream = null;
        try {
            inputStream = url.openStream();
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        }

        Optional<User> userOptional = userContext.getAuthenticatedUser();

        Source source = new Source();
        source.setUser(userOptional
                .orElseThrow(() -> new RuntimeException("Not authenticated")));
        source.setName(name);
        source.setDefaultValue(false);

        sourceRepository.save(source);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<User> userOptional = userContext.getAuthenticatedUser();
        sourceRepository.deleteByIdAndUser(id, userOptional
                .orElseThrow(() -> new RuntimeException("Not authenticated")));
    }

    @Override
    @Transactional
    public void enableSources(List<Integer> ids) {
        enableDefaultSources(ids);

        Optional<User> userOptional = userContext.getAuthenticatedUser();
        if (userOptional.isPresent()) {
            sourceRepository.disableOtherSources(ids, userOptional.get().getId());
            sourceRepository.enableSources(ids, userOptional.get().getId());
        }
    }

    @Transactional
    public void enableDefaultSources(List<Integer> ids) {
        sourceRepository.disableOtherSources(ids);
        sourceRepository.enableSources(ids);
    }

    @Override
    public List<Source> getSources() {
        Optional<User> userOptional = userContext.getAuthenticatedUser();
        if (userOptional.isPresent()) {
            return sourceRepository.findByUserOrDefaultValue(userOptional.get(), true);
        }
        return sourceRepository.findAllByDefaultValue(true);
    }
}
