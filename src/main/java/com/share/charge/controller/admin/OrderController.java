package com.share.charge.controller.admin;

import com.github.pagehelper.PageInfo;
import com.share.charge.common.PageHelperUtil;
import com.share.charge.service.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin/order")
@SessionAttributes({"ORDER_CURRENT_PAGE","ORDER_SEARCH_GUESTID"})
public class OrderController {
    @Autowired
    AdminOrderService adminOrderService;

    @RequestMapping(value = {""},method = RequestMethod.GET)
    public String findOrderByGuestId(Integer guestId, Integer pageNum,
                                     Model model, HttpSession session){
        PageInfo pageInfo;
        if(pageNum == null){
            pageNum = 1;
        }
        //翻页的时候不会带guestId，所以这里自动加载上
        if(guestId == null){
            guestId = (Integer) session.getAttribute("ORDER_SEARCH_GUESTID");
        }

        pageInfo = adminOrderService.findOrderByGuestId(guestId,pageNum);
        setListModel(pageInfo, model);
        return "admin/order/list";
    }
    private void setListModel(PageInfo pageInfo, Model model) {
        if(pageInfo.getTotal()==0){
            model.addAttribute("pageKeySet", "EMPTY");
        }
        else {
            model.addAttribute("pageKeySet", PageHelperUtil.getKeySetFromPageInfo(pageInfo));

        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("ORDER_CURRENT_PAGE",pageInfo.getPageNum());


    }


    @RequestMapping(value = {"/search"},method = RequestMethod.GET)
    public String searchChargeByCabinetId(Integer guestId, Model model , HttpSession session) {
        System.out.println("guest:id:"+guestId);
        model.addAttribute("ORDER_SEARCH_GUESTID",guestId);
        session.setAttribute("ORDER_SEARCH_GUESTID",guestId);
//        PageInfo pageInfo;
//        pageInfo = adminOrderService.findOrderByGuestId(guestId,1);
//        setListModel(pageInfo, model);
        return findOrderByGuestId(guestId,1,model,session);
    }
}
