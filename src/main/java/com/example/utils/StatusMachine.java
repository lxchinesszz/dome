package com.example.utils;

import com.example.domain.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @Package: com.example.utils
 * @Description: 状态机, 将发送的修改状态构建成json对象
 * @author: liuxin
 * @date: 17/3/20 下午2:34
 */
public class StatusMachine {
    private final static Logger logger= LoggerFactory.getLogger(StatusMachine.class);
    private  Map<String, Object>  paidMap = new HashedMap();
    private  Map<String, Object>  docMap = new HashedMap();
    private  ArrayList<NextStatus>  status = new ArrayList<NextStatus>();
    private static Gson gson = new Gson();
    private static boolean flag=false;

    /**
     * @param canCancel 是否可以退款1，为可以 0不能退款
     * @param status1
     * @return
     */
    public  Map<String, Object> buildMapStatus(int canCancel, OrderStatus... status1) {
        docMap.put("canCancel", canCancel+"");
        Arrays.stream(status1).forEach((x) -> {
            status.add(new NextStatus(x));
        });
        paidMap.put("statuses", status);
        paidMap.put("opts", docMap);
        flag=true;
        return paidMap;
    }

    public  StatusMachine buildJsonStatus(int canCancel, OrderStatus... status1) {
        docMap.put("canCancel", canCancel+"");
        Arrays.stream(status1).forEach((x) -> {
            status.add(new NextStatus(x));
        });
        paidMap.put("statuses", status);
        paidMap.put("opts", docMap);
        flag=true;
        return this;
    }


    public StatusMachine setPrettyPrinting(){
        gson=new GsonBuilder().setPrettyPrinting().create();
        return this;
    }

    public  String toJson() {
        if (!flag)
            return "请先执行buildStatus方法填充状态信息";
        return gson.toJson(paidMap);
    }


    public Map<String, Object> getPaidMap() {
        return paidMap;
    }

    public void setPaidMap(Map<String, Object> paidMap) {
        this.paidMap = paidMap;
    }

    public static void main(String[] args) {
        StatusMachine sm=new StatusMachine();
        //输出map
        System.out.println(sm.buildMapStatus(0, OrderStatus.orderde, OrderStatus.be_paid).get("opts"));
        //非格式化输出
        System.out.println(sm.buildJsonStatus(0, OrderStatus.orderde, OrderStatus.be_paid).toJson());
        //格式化输出json
        System.out.println(sm.buildJsonStatus(0, OrderStatus.orderde, OrderStatus.be_paid).setPrettyPrinting().toJson());
    }
}
