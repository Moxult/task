package ru.max.task.adapters.endpoints;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.max.task.adapters.repositories.CustomerRepository;
import ru.max.task.application.security.ApplicationCustomer;



@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthorizedController {

    @NonNull CustomerRepository customerRepository;

    @GetMapping("/authorized")
    public ModelAndView showAuthorizedPage(ModelAndView mav, @AuthenticationPrincipal ApplicationCustomer userPrincipal) {

        var authCustomer = customerRepository
                .findByLoginOrThrow(userPrincipal.getUsername());

        mav.addObject("login", authCustomer.getLogin());
        mav.addObject("email", authCustomer.getEmail());
        mav.setViewName("authorized_zone");

        return mav;
    }

    @GetMapping("/login")
    public String showAuthForm() {

        return "login";
    }

}
