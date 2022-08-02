package by.academy.pharmacy_spring_boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    public static final String HOME = "home";

    @GetMapping

    public String home(Model model) {
        return HOME;
    }
}
