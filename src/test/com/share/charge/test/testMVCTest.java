package com.share.charge.test;

import com.share.charge.controller.LoginGuestController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class testMVCTest {

    @Test
    public void hello() {
    }

    @Test
    public void testThy() throws Exception{
        testMVC controller = new testMVC();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/thy"))
                .andExpect(model().attributeExists("loginApi"))
                .andExpect(view().name("test/testthy.html"));
    }
}