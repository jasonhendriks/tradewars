package ca.hendriks.tradewars.security.user.registration;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class RegistrationController {

    private final UserCreationService userCreationService;

    RegistrationController(final UserCreationService userCreationService) {
        this.userCreationService = userCreationService;
    }

    @RequestMapping(value = "signup")
    public ModelAndView registrationForm() {
        return new ModelAndView("registrationPage", "user", new UserCreationView("", "", "", ""));
    }

    @RequestMapping(value = "user/register")
    public ModelAndView registerUser(@Valid final UserCreationView user, final BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("registrationPage", "user", user);
        }
        try {
            userCreationService.registerNewUser(user);
        } catch (final EmailExistsException e) {
            result.addError(new FieldError("user", "email", e.getMessage()));
            return new ModelAndView("registrationPage", "user", user);
        }

        return new ModelAndView("redirect:/login");
    }

}
