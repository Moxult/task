package ru.max.task.adapters.endpoints.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.max.task.application.services.exceptions.ExternalSystemTimeOutException;
import ru.max.task.application.services.exceptions.ResponseHasErrorException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SendingRegistrationAdvice {

    public static final String ERROR_VIEW = "error";

    @ExceptionHandler(value = ExternalSystemTimeOutException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ModelAndView registrationTimeOutErrorHandler(HttpServletRequest req) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("url", req.getRequestURL());
        mav.addObject("errorDescription", "Timeout response");
        mav.setViewName(ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = ResponseHasErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView registrationRejectedErrorHandler(HttpServletRequest req, Exception e) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("url", req.getRequestURL());
        mav.addObject("errorDescription", e.getMessage());
        mav.setViewName(ERROR_VIEW);
        return mav;
    }
}
