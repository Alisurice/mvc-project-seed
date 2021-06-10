package com.share.charge.controller;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guest")
public class LoginGuestController {
    private static final Logger LOGGER = Logger.getLogger(LoginGuestController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String guestLogin(){
        LOGGER.debug("#LoginGuestController#guest login");
        return "guest/login";
    }

}
