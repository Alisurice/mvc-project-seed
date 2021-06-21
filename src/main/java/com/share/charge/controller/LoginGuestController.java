package com.share.charge.controller;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.service.LoginRegisterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller

public class LoginGuestController {
    private static final Logger LOGGER = Logger.getLogger(LoginGuestController.class);

    @RequestMapping(value = "/admin/login",method = RequestMethod.GET)
    public String login(HttpSession session, HttpServletRequest request){
        LOGGER.debug("admin login");
        setLoginPage(session,
                request,
                "Admin",
                "/admin/login");
//        LOGGER.debug("return url#"+request.getContextPath()+"/index.jsp");
        return "/index.jsp";
    }


    @Autowired
    LoginRegisterService loginRegisterService;
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String loginApi(UmsAdmin umsAdmin, HttpSession session, HttpServletRequest request){
        LOGGER.debug("POST#admin login");
        if(loginRegisterService.adminLogin(umsAdmin)==false){
            LOGGER.debug("POST#login failed");
            session.setAttribute("loginStatus", "登陆失败");
            setLoginPage(session,
                    request,
                    "Admin",
                    "/admin/login");
        }
        else{
            LOGGER.debug("loginApi#login success");
            return "/guest/login.html";
        }
        return "/501";
    }

    /**
     *
     * @param session
     * @param loginApi just input relative path,
     *                 and request.getContextPath() will joint it.
     *                 we must do the because <form action=${loginApi}></form>
     * @param userType
     */
    private void setLoginPage(HttpSession session,HttpServletRequest request,
                              String userType, String loginApi){
//        session.setAttribute("loginApi", request.getContextPath()+loginApi);
//        使用了jstl
//                <form action="<c:url value='${loginApi}'/>" method="post">
//                所以不需要加request
        session.setAttribute("loginApi", loginApi);


        session.setAttribute("userType", userType);
    }

}
