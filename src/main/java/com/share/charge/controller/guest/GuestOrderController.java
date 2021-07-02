package com.share.charge.controller.guest;

import com.github.pagehelper.PageInfo;
import com.share.charge.common.PageHelperUtil;
import com.share.charge.mybatis.generator.model.OmsOrder;
import com.share.charge.mybatis.generator.model.UmsGuest;
import com.share.charge.service.AdminCabinetService;
import com.share.charge.service.AdminOrderService;
import com.share.charge.vo.VoCreateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/guest/order")
public class GuestOrderController {
    @Autowired()
    AdminOrderService adminOrderService;

    @RequestMapping("/exit")
    public String guestExit(HttpSession session){
        session.removeAttribute("LOGIN_USER");
        session.removeAttribute("LOGIN_USER_TYPE");
        return "redirect:/index.html";
    }

    @RequestMapping("/create")
    public @ResponseBody VoCreateOrder createOrder(
            @RequestBody Map<String,Integer> map, Model model, HttpSession session){
        VoCreateOrder result = new VoCreateOrder();
        Integer chargeId = (Integer) map.get("chargeId");
        if(chargeId == null){

            result.setSuccess(false);
            result.setMessage("请选择充电宝");
            return result;
        }
        else {
            //获取用户顺带判断登录
            if(session.getAttribute("LOGIN_USER")==null){
                result.setSuccess(false);
                result.setMessage("未登录");
                return result;
            }
            if(session.getAttribute("LOGIN_USER").getClass() == UmsGuest.class){
                UmsGuest umsGuest = (UmsGuest) session.getAttribute("LOGIN_USER");
                if(umsGuest.getId()!= null){

                    OmsOrder omsOrder =
                            adminOrderService.createOrder(umsGuest.getId(),chargeId);
                    if(omsOrder!=null){
                        result.setSuccess(true);
                        result.setOrderId(omsOrder.getId());

                        Date borrowDate = new Date(omsOrder.getBorrowTimeSeconds());

                        result.setCreateTime(borrowDate);

                        return result;
                    }
                }
            }
            result.setSuccess(false);
            return result;
        }
    }

    @RequestMapping("/borrowlist")
    public String showBorrowList(HttpSession session,Model model){
        Integer guestId = (Integer) session.getAttribute("LOGIN_USER_ID");
        if(guestId==null){
            throw new NullPointerException("用户登录信息存在异常");
        }
        List<OmsOrder> list = adminOrderService.findBorrowByGuestId(guestId);

        if(list.size()==0){
            model.addAttribute("pageKeySet", "EMPTY");
        }
        else {
            model.addAttribute("pageKeySet",
                    PageHelperUtil.getKeySetFromRecord(list.get(0)));

        }
        model.addAttribute("pageInfo",list);

        return "guest/order/borrowlist";
    }

    @RequestMapping("/returnCharge")
    @ResponseBody
    public String returnCharge(Integer orderId,Integer cabinetId,
                               HttpSession session,Model model){
        Integer guestId = (Integer) session.getAttribute("LOGIN_USER_ID");
        if(guestId==null){
            throw new NullPointerException("用户登录信息存在异常");
        }
        OmsOrder omsOrder = adminOrderService.returnCharge(orderId,cabinetId);
        if(omsOrder!=null){
            return "归还成功\n"+"账单："+omsOrder.getmBill()+" 结余："+omsOrder.getmBalance();
        }
        return "归还失败";
    }



    @RequestMapping(value = "/balance",method = RequestMethod.POST)
    @ResponseBody
    public String returnBalance(Integer orderId, HttpSession session){
        Integer guestId = (Integer) session.getAttribute("LOGIN_USER_ID");
        if(guestId==null){
            throw new NullPointerException("用户登录信息存在异常");
        }
        Double balance = adminOrderService.returnBalance(orderId);
        if(balance!=null){
            return "成功结余："+balance;
        }
        return "结余失败";
    }

    @RequestMapping(value = {""},method = RequestMethod.GET)
    public String findOrderByGuestId(
                                     Model model, HttpSession session){
        PageInfo pageInfo;

        Integer guestId = (Integer) session.getAttribute("LOGIN_USER_ID");
        if(guestId==null){
            throw new NullPointerException("用户登录信息存在异常");
        }

        pageInfo = adminOrderService.findOrderByGuestId(guestId,0);
        setListModel(pageInfo, model);
        return "guest/order/list";
    }
    private void setListModel(PageInfo pageInfo, Model model) {
        if(pageInfo.getTotal()==0){
            model.addAttribute("pageKeySet", "EMPTY");
        }
        else {
            model.addAttribute("pageKeySet", PageHelperUtil.getKeySetFromPageInfo(pageInfo));

        }
        model.addAttribute("pageInfo",pageInfo);


    }
}
