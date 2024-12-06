package ca.hendriks.tradewars.security.user.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class LoginEndpoint {

    @RequestMapping("/login")
    public String login() throws InterruptedException {
        Thread.sleep(1000);
        return "loginPage";
    }

}
