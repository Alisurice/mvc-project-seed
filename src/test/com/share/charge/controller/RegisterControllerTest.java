package com.share.charge.controller;

import com.share.charge.vo.LoginRegisterVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")

public class RegisterControllerTest {
    @Resource
    private RegisterController registerController;

    private MockMvc mockmvc;
    @Before
    public void init(){
        mockmvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }



    @Test
    public void register() throws Exception {
        LoginRegisterVO loginRegisterVO =
                new LoginRegisterVO("注册","admin/register",null,"admin");
        mockmvc.perform(get("/admin/register"))
                .andExpect(view().name("WEB-INF/views/index.html"))
                .andExpect(model().attribute("indexVO",loginRegisterVO));
    }


    @Test
    public void registerApi() throws Exception {
        mockmvc.perform(
                post("/admin/register")
                        .param("username","1")
                        .param("password","1"))
                .andExpect(model().attributeExists("indexVO"))
                .andExpect(model().attribute("indexVO","test"))
                .andExpect(view().name("/guest/login.html"));
    }
}


