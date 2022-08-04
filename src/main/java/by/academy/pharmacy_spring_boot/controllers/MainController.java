package by.academy.pharmacy_spring_boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.academy.pharmacy_spring_boot.constants.Constants.HOME;
import static by.academy.pharmacy_spring_boot.constants.Constants.HOME_PAGE;

@Controller
@RequiredArgsConstructor
@RequestMapping(HOME_PAGE)
public class MainController {

    @GetMapping

    public String home(Model model) {
        return HOME;
    }
}
