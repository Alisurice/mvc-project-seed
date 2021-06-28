package com.share.charge.controller.admin;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ChargeControllerTest {
    @Resource
    private ChargeController chargeController;

    private MockMvc mockmvc;
    @Before
    public void init(){
        mockmvc = MockMvcBuilders.standaloneSetup(chargeController).build();
    }

    @Test
    public void searchChargeByCabinetId() throws Exception {
        mockmvc.perform(get("/admin/charge/search").param("cabinetId","2"))
                .andExpect(model().attribute("CHARGE_SEARCH_CABINET","test"));

    }
}