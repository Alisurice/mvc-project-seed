package com.share.charge.controller.admin;

import com.share.charge.controller.RegisterController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AdminControllerTest {
    @Resource
    private AdminController adminController;

    private MockMvc mockmvc;
    @Before
    public void init(){
        mockmvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void guestManage() {
    }

    @Test
    public void updateGuest() throws Exception {
        mockmvc.perform(get("/admin/guest-manage/update/1/test"))
                .andExpect(model().attribute("resultMessage","test"));

    }

    @Test
    public void deleteGuest() {
    }
}