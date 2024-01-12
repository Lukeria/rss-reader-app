package com.course.spp.rss_reader_app.controller;

import com.course.spp.rss_reader_app.dto.UserDto;
import com.course.spp.rss_reader_app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signup(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute("user") UserDto userDto,
                             Model model) {

        try {
            userService.addUser(userDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
        return "redirect:/login";
    }
}
