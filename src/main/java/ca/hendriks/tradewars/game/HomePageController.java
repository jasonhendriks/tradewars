package ca.hendriks.tradewars.game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping
    String homePage() {
        return "home";
    }

}
