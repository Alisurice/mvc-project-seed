package com.share.charge.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.share.charge.common.PageHelperUtil;
import com.share.charge.mybatis.generator.model.UmsGuest;
import com.share.charge.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@RequestMapping("/admin")
@SessionAttributes("GUEST_MANAGE_CURRENT_PAGE")
@Api(value="用户controller",tags={"用户操作接口"})
public class AdminController {
    private static final Logger LOGGER = Logger.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @ApiOperation(value = "测试api", notes = "第一个测试")
    @RequestMapping(value = "/guest-manage",method = RequestMethod.GET)
    public String guestManage(Integer pageNum, Model model) throws IOException {

        setBaseModel(pageNum, model);
        return "admin/guest-manage";
    }
    @RequestMapping(value = "/guest-manage/update/{id}/{password}",method = RequestMethod.GET)
    public String updateGuest(@PathVariable int id, @PathVariable String password,
                              Model model) throws IOException {
        UmsGuest umsGuest = new UmsGuest();
        umsGuest.setId(id);
        umsGuest.setPassword(password);
        if(adminService.updateGuestPassword(umsGuest)==1){
            model.addAttribute("resultMessage","更改密码成功");
        }
        else {
            model.addAttribute("resultMessage","更改密码失败");
        }
        setBaseModel((Integer)model.getAttribute("GUEST_MANAGE_CURRENT_PAGE"),model);
        return "admin/guest-manage";
    }

    @RequestMapping(value = "/guest-manage/delete/{id}",method = RequestMethod.GET)
    public String deleteGuest(@PathVariable Integer id, Model model) throws IOException {
        if(adminService.deleteGuestById(id)==1){
            model.addAttribute("resultMessage","删除成功");
        }
        else {
            model.addAttribute("resultMessage","删除失败");
        }
        setBaseModel((Integer)model.getAttribute("GUEST_MANAGE_CURRENT_PAGE"),model);
        return "admin/guest-manage";
    }
    private void setBaseModel(Integer pageNum, Model model) {
        if(pageNum == null){
            pageNum = 1;
        }
        PageInfo pageInfo = adminService.findAllGuest(pageNum);
        if(pageInfo.getTotal()==0){
            model.addAttribute("pageKeySet", "EMPTY");
        }
        else {
            model.addAttribute("pageKeySet", PageHelperUtil.getKeySetFromPageInfo(pageInfo));

        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("GUEST_MANAGE_CURRENT_PAGE",pageInfo.getPageNum());
    }
}
