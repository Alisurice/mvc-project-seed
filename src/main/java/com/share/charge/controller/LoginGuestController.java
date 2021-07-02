package com.share.charge.controller;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;
import com.share.charge.service.LoginRegisterService;
import com.share.charge.vo.LoginRegisterVO;
import com.sun.deploy.util.SessionState;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;


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
    public String loginApi(UmsAdmin umsAdmin,Model model,HttpSession session){
        LOGGER.debug("POST#admin login");
        if(loginRegisterService.adminLogin(umsAdmin)==false){
            LOGGER.debug("POST#login failed");
            loginRegisterVO.setResultMessage("登陆失败");
            model.addAttribute("indexVO",loginRegisterVO);
            return "index";
        }
        else{
            session.setAttribute("LOGIN_USER",umsAdmin);
            session.setAttribute("LOGIN_USER_TYPE","admin");
            LOGGER.debug("loginApi#login success");
            loginRegisterVO.setResultMessage("登陆成功");
            model.addAttribute("USER",umsAdmin);
            model.addAttribute("indexVO",loginRegisterVO);
            return "redirect:/admin/mainpage.html";
        }

    }

    @RequestMapping(value = "/guest/login", method = RequestMethod.POST)
    public String guestLogin(UmsGuest umsGuest, Model model,HttpSession session){
        LOGGER.debug("POST#guestLogin");
        if(loginRegisterService.guestLogin(umsGuest)==false){
            LOGGER.debug("POST#login failed");
            model.addAttribute("resultMessage","登陆失败");
            return "forward:/index.jsp";
        }
        else{
            LOGGER.debug("loginApi#login success");
            loginRegisterVO.setResultMessage("登陆成功");
            model.addAttribute("USER",umsGuest);
            model.addAttribute("indexVO",loginRegisterVO);
            model.addAttribute("resultMessage",loginRegisterVO.getResultMessage());
            session.setAttribute("LOGIN_USER",umsGuest);
            session.setAttribute("LOGIN_USER_ID",umsGuest.getId());
            session.setAttribute("LOGIN_USER_TYPE","guest");
            return "redirect:/guest/mainpage.html";
        }

    }


    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String guestLogin(HttpSession session, SessionStatus sessionStatus){
        System.out.println("loginout");
        sessionStatus.setComplete();
        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }
        return "redirect:/index.html";
    }
}
