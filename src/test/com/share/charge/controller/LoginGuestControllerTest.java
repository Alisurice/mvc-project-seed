package com.share.charge.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class LoginGuestControllerTest {

    @Test
    public void testlogin() throws Exception{
        LoginGuestController controller = new LoginGuestController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/admin/login"))
                .andExpect(view().name("/index.jsp"));
    }

    @Test
    public void loginApi() {
    }
}