package io.github.hobbybuddy.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    @RequestMapping("/")
    public String mainPage()
    {
        return "main";
    }
}
