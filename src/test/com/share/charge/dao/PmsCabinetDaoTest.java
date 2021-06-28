package com.share.charge.dao;

import com.share.charge.vo.VoCabinetMes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PmsCabinetDaoTest {

    @Resource
    PmsCabinetDao pmsCabinetDao;
    @Test
    public void findCabinetByPosition() {
        System.out.println(pmsCabinetDao.findCabinetByPosition("%321%"));
    }

    @Test
    public void findByPosition(){
        System.out.println(pmsCabinetDao.findByPosition("%%"));

    }
    @Test
    public void testFind(){
//        System.out.println(pmsCabinetDao.testFind("%%"));
        System.out.println(pmsCabinetDao.findVoCabinetById(1));
    }
}