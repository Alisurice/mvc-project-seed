package com.share.charge.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public  class PageHelperUtil {
    public static List<String> getKeySetFromRecord(Object record) {
        if(record==null){
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.valueToTree(record);
        Iterator<String> keys = objectNode.fieldNames();

        List<String> keyList = new ArrayList<>();
        while(keys.hasNext()){
            String key = keys.next();
            keyList.add(key);
//            System.out.println("key键是:"+key);
        }
//        System.out.println("out:"+ keyList);
        return keyList;
    }
    public static List<String> getKeySetFromPageInfo(PageInfo pageInfo){
//        System.out.println(pageInfo.getList());
        return getKeySetFromRecord(pageInfo.getList().get(0));
    }
}
