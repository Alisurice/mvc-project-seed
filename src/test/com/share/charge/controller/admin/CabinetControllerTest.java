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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CabinetControllerTest {
    @Resource
    private CabinetController cabinetController;

    private MockMvc mockmvc;
    @Before
    public void init(){
        mockmvc = MockMvcBuilders.standaloneSetup(cabinetController).build();
    }

    @Test
    public void searchChargeByCabinetId() throws Exception {
        mockmvc.perform(get("/admin/cabinet"))
                .andExpect(model().attribute("CABINET_CURRENT_PAGE",1))
                .andExpect(model().attribute("CABINET_SEARCH_POSITION",""));

    }
    @Test
    public void testSearch() throws Exception {
        mockmvc.perform(get("/admin/cabinet").param("position","1"))
                .andExpect(model().attribute("CABINET_CURRENT_PAGE",1))
                .andExpect(model().attribute("CABINET_SEARCH_POSITION",""));

    }

    @Test
    public void insertCabinet() throws Exception {

        mockmvc.perform(post("/admin/cabinet")
                .param("position","1")
                .param("maxCharge","1")
                .param("id","1"))

                .andExpect(model().attribute("insertResultMessage",""));
    }
}