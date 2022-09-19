package ru.trainithard.pollerbot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.trainithard.pollerbot.service.AdminManager;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminManager adminManager;

    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", adminManager.getUsers());
        return mav;
    }
}
