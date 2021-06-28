package com.share.charge.controller.admin;

import com.github.pagehelper.PageInfo;
import com.share.charge.common.PageHelperUtil;
import com.share.charge.mybatis.generator.model.PmsCharge;
import com.share.charge.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin/charge")
@SessionAttributes({"GUEST_MANAGE_CURRENT_PAGE","CHARGE_SEARCH_CABINET","CURRENT_CHARGE"})
public class ChargeController {
    private static final Logger LOGGER = Logger.getLogger(ChargeController.class);

    @Autowired
    AdminService adminService;

    @RequestMapping(value = {"/list",""},method = RequestMethod.GET)
    public String showChargeList(Integer pageNum, Model model, HttpSession session) throws IOException {
        PageInfo pageInfo;

        if(pageNum == null){
            pageNum =(Integer)session.getAttribute("GUEST_MANAGE_CURRENT_PAGE");
            if(pageNum == null){
                pageNum = 1;
            }
        }
        Integer cabinetId = (Integer)session.getAttribute("CHARGE_SEARCH_CABINET");
        LOGGER.debug("cabinetId:"+cabinetId);
        if(cabinetId != null){
            pageInfo = adminService.findChargeByCabinetId(cabinetId,pageNum);
            setListModel(pageInfo, model,cabinetId);
        }
        else {
            pageInfo = adminService.findAllCharge(pageNum);
        }
        setListModel(pageInfo, model,cabinetId);

        LOGGER.debug("###showChargeList:cabinetId:"+ session.getAttribute("CHARGE_SEARCH_CABINET"));
        return "admin/charge/list";
    }
    @RequestMapping(value = {"/search"},method = RequestMethod.GET)
    public String searchChargeByCabinetId(Integer cabinetId,
                                          Integer pageNum,
                                          Model model,
                                          HttpSession session) throws IOException {
//        只有search能够改变筛选条件
        if(pageNum == null){
            pageNum = 1;
        }
        if(cabinetId != null){
            PageInfo pageInfo = adminService.findChargeByCabinetId(cabinetId,pageNum);
            setListModel(pageInfo, model,cabinetId);
            return "admin/charge/list";
        }
        else {
            session.setAttribute("CHARGE_SEARCH_CABINET",null);

            return showChargeList(pageNum,model,session);
        }


    }
    private void setListModel(PageInfo pageInfo, Model model,Integer cabinetId) {
        if(pageInfo.getTotal()==0){
            model.addAttribute("pageKeySet", "EMPTY");
        }
        else {
            model.addAttribute("pageKeySet", PageHelperUtil.getKeySetFromPageInfo(pageInfo));

        }
        model.addAttribute("CHARGE_SEARCH_CABINET",cabinetId);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("GUEST_MANAGE_CURRENT_PAGE",pageInfo.getPageNum());
        LOGGER.debug("###setListModel:cabinetId:"+cabinetId);

    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteCharge(@PathVariable Integer id, Model model,
                               HttpSession session) throws IOException {
        if(adminService.deleteChargeById(id)==1){
            model.addAttribute("resultMessage","删除成功");
        }
        else {
            model.addAttribute("resultMessage","删除失败");
        }

        return showChargeList(null,model,session);
    }


    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String showUpdateChargePage(Integer id, Model model){
        PmsCharge pmsCharge=null;
        //确定id
        if(id == null){
            id = (Integer)model.getAttribute("CURRENT_CHARGE");
        }
        if(id != null){
            model.addAttribute("CURRENT_CHARGE",id);
            //输入的id可能并不存在
            pmsCharge = adminService.findChargeById(id);

        }
//        id为null时pmscharge必为null，然后查找可能查找到空，为了thymeleaf显示，必须返回一个对象
        if(pmsCharge == null){
            model.addAttribute("charge",new PmsCharge());
            model.addAttribute("resultMessage","无法找到充电宝，id："+id);
        }
        else{
            model.addAttribute("charge",pmsCharge);
        }
        return "admin/charge/edit";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateCharge(@ModelAttribute PmsCharge charge, Model model) throws IOException {
        if(charge.getId()==null){
            model.addAttribute("resultMessage","请输入充电宝id");
        }
        else if(adminService.updateCharge(charge)==1){
            model.addAttribute("resultMessage","更新成功");
        }
        else {
            model.addAttribute("resultMessage","更新失败，请确认充电宝id和机柜id是否存在");
        }
        return showUpdateChargePage(charge.getId(),model);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String insertCharge(PmsCharge charge, Model model,HttpSession session) throws IOException {

        charge.setBorrowStatus("在柜");

        if(charge.getPowerLevel()==null){
            model.addAttribute("resultMessage","请输入充电宝电量水平");
        }
        else if(charge.getCabinetId()==null){
            model.addAttribute("resultMessage","请输入充电宝所属机柜id");
        }
        else if(adminService.insertCharge(charge)==1){
            model.addAttribute("resultMessage","新增充电宝成功，id："+charge.getId());
        }
        else {
            model.addAttribute("resultMessage","新增失败，请确认机柜id是否存在以及是否有空余");
        }
        return showChargeList(null,model,session);
    }
}
