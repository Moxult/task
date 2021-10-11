package ru.max.task.adapters.endpoints;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.max.task.application.api.RegistrationApi;
import ru.max.task.application.web.dto.RegistrationInput;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationController {

    @NonNull RegistrationApi registrationApi;

    @GetMapping("/sign-up")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationInput", new RegistrationInput());

        return "sign_up";
    }

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerCustomer(@NonNull @Valid RegistrationInput registrationInput, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "sign_up";
        }

        registrationApi.registerNewCustomer(registrationInput);

        return "register_success";
    }
}
