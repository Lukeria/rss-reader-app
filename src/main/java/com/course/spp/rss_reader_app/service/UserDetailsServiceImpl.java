package com.course.spp.rss_reader_app.service;


import com.course.spp.rss_reader_app.domain.User;
import com.course.spp.rss_reader_app.domain.UserDetailsImpl;
import com.course.spp.rss_reader_app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.getByUsername(username);
        return new UserDetailsImpl(optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " is not found")));
    }
}
