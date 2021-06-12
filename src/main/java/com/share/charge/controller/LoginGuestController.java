package com.share.charge.controller;
import com.share.charge.dao.MyUmsAdminMapper;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginGuestController {
    private static final Logger LOGGER = Logger.getLogger(LoginGuestController.class);

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String guestLogin(){
        LOGGER.debug("admin login");
        return "guest/login";
    }

    @Autowired
    private MyUmsAdminMapper myUmsAdminMapper;
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String guestLogin(UmsAdmin umsAdmin){
        LOGGER.debug("POST#admin login");

        return "guest/login";
    }

}
