package com.course.spp.rss_reader_app.service;

import com.course.spp.rss_reader_app.exceptions.PasswordsNotMatchedException;
import com.course.spp.rss_reader_app.exceptions.UsernameExistsException;
import com.course.spp.rss_reader_app.domain.User;
import com.course.spp.rss_reader_app.dto.UserDto;
import com.course.spp.rss_reader_app.repository.AuthorityRepository;
import com.course.spp.rss_reader_app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void addUser(UserDto userDto) {
        if (userDto.getPassword() == null || userDto.getConfirmPassword() == null ||
                !userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new PasswordsNotMatchedException("Passwords don't match. Try again");
        }

        if (userRepository.getByUsername(userDto.getUsername()).isPresent()) {
            throw new UsernameExistsException("User with username " + userDto.getUsername() + " already exists");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAuthority(authorityRepository.getByName("ROLE_USER"));
        user.setEnabled(true);

        userRepository.save(user);
    }
}
