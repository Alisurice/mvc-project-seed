package com.share.charge.controller.guest;

import com.github.pagehelper.PageInfo;
import com.share.charge.common.PageHelperUtil;
import com.share.charge.mybatis.generator.model.PmsCabinet;
import com.share.charge.mybatis.generator.model.PmsCharge;
import com.share.charge.service.AdminCabinetService;
import com.share.charge.vo.VoCabinetMes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/guest/cabinet")
@SessionAttributes({"CABINET_CURRENT_PAGE","CABINET_SEARCH_POSITION"})
public class GuestCabinetController {
    private static final Logger LOGGER = Logger.getLogger(GuestCabinetController.class);

    @Autowired()
    AdminCabinetService adminCabinetService;


    @RequestMapping(value = {"/search",""},method = RequestMethod.GET)
    public String searchChargeByCabinetId(String position,Integer pageNum, Model model) throws IOException {

        if(pageNum == null){
            pageNum =(Integer)model.getAttribute("CABINET_CURRENT_PAGE");
            if(pageNum == null){
                pageNum = 1;
            }
        }
        if(position == null){
            position =(String) model.getAttribute("CABINET_SEARCH_POSITION");
            if(position == null){
                position = "";
            }
        }

        PageInfo pageInfo = adminCabinetService.findCabinetByPosition(position,pageNum);
        setListModel(pageInfo, model,position);
        return "guest/cabinet/list";

    }

    private void setListModel(PageInfo pageInfo, Model model,String position ) {
        if(pageInfo.getTotal()==0){
            model.addAttribute("pageKeySet", "EMPTY");
        }
        else {
            model.addAttribute("pageKeySet", PageHelperUtil.getKeySetFromPageInfo(pageInfo));

        }

        model.addAttribute("CABINET_SEARCH_POSITION",position);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("CABINET_CURRENT_PAGE",pageInfo.getPageNum());

    }


    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public String insertPage(){
        return "guest/cabinet/insert";
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String insertCabinet(PmsCabinet pmsCabinet,Model model){
        String insertResultMessage;

        if(pmsCabinet.getMaxCharge()==null||pmsCabinet.getMaxCharge()<0){
            insertResultMessage = "机柜最大容量应大于等于0";
        }
        else if(pmsCabinet.getPosition()==null){
            insertResultMessage = "请输入机柜所在位置";
        }
        else {
            int insertResult =adminCabinetService.insertCabinet(pmsCabinet);
            if(insertResult==1){
                insertResultMessage =
                        "新增机柜成功，机柜id："+pmsCabinet.getId()
                        +"\n机柜位置："+pmsCabinet.getPosition()
                        +"\n机柜最大容量："+pmsCabinet.getMaxCharge();
            }
            else {
                insertResultMessage="插入数据库失败:"+insertResult;
            }

        }
        model.addAttribute("insertResultMessage",insertResultMessage);

        return "guest/cabinet/insert";
    }


    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateCabinetPage(Integer id, Model model){
        VoCabinetMes voCabinetMes=null;
        if(id != null){
            voCabinetMes = adminCabinetService.findVoCabinetById(id);
            if(voCabinetMes == null){
                model.addAttribute("voCabinetMes",new PmsCabinet());
                model.addAttribute("resultMessage","无法找到机柜，id："+id);
            }
            else {
                model.addAttribute("voCabinetMes",voCabinetMes);
            }
        }
        else {
            //显示空数据用
            model.addAttribute("voCabinetMes",new PmsCabinet());
        }
//        List<String> keySet = new ArrayList<>();
//        keySet.add("id");
//        keySet.add("position");
//        keySet.add("maxCharge");

        if(voCabinetMes.getChargeList().size()!=0){
            model.addAttribute("chargeKeySet",
                    PageHelperUtil.getKeySetFromRecord(voCabinetMes.getChargeList().get(0)));
        }
        else {
            model.addAttribute("chargeKeySet",
                    PageHelperUtil.getKeySetFromRecord(new PmsCharge()));
        }



        return "guest/cabinet/edit";
    }
}
