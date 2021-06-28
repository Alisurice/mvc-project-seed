package com.share.charge.controller;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.service.LoginRegisterService;
import com.share.charge.vo.LoginRegisterVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes("USER")
public class LoginGuestController {
    private static final Logger LOGGER = Logger.getLogger(LoginGuestController.class);
    private LoginRegisterVO loginRegisterVO =
            new LoginRegisterVO("登录","/admin/login",null,"admin");


    @RequestMapping(value = "/admin/login",method = RequestMethod.GET)
    public String login(Model model){
        LOGGER.debug("admin login");

        model.addAttribute("indexVO",loginRegisterVO);
        return "index";
    }


    @Autowired
    LoginRegisterService loginRegisterService;
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String loginApi(UmsAdmin umsAdmin,Model model){
        LOGGER.debug("POST#admin login");
        if(loginRegisterService.adminLogin(umsAdmin)==false){
            LOGGER.debug("POST#login failed");
            loginRegisterVO.setResultMessage("登陆失败");
            model.addAttribute("indexVO",loginRegisterVO);
            return "index";
        }
        else{
            LOGGER.debug("loginApi#login success");
            loginRegisterVO.setResultMessage("登陆成功");
            model.addAttribute("USER",umsAdmin);
            model.addAttribute("indexVO",loginRegisterVO);
            return "redirect:/admin/mainpage.html";
        }

    }


}
