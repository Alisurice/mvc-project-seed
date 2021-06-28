package com.share.charge.controller;

import com.share.charge.dto.LoginRegisterDTO;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.service.LoginRegisterService;
import com.share.charge.vo.LoginRegisterVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class RegisterController {

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);

    @RequestMapping(value = "/admin/register",method = RequestMethod.GET)
    public String register(Model model){
        LOGGER.debug("/admin register");
        LoginRegisterVO loginRegisterVO =
                new LoginRegisterVO("注册","/admin/register",
                        null,"admin");
        model.addAttribute("indexVO",loginRegisterVO);
        return "index";
    }


    @Autowired
    LoginRegisterService loginRegisterService;
    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    public String registerApi(UmsAdmin umsAdmin, Model model){
        LoginRegisterDTO loginRegisterDTO = loginRegisterService.adminRegister(umsAdmin);
        LoginRegisterVO loginRegisterVO =
                new LoginRegisterVO("注册","/admin/register",
                        loginRegisterDTO.getMessage(),"admin");
        model.addAttribute("indexVO",loginRegisterVO);
        if(loginRegisterDTO.isSuccess()){
            LOGGER.debug("POST#register success");
            return "/guest/login.html";
        }
        else{
            return "index";
        }

    }


}
