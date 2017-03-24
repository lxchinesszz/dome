package com.example.utils;
import com.google.gson.Gson;

/**
 * Created by liuxin on 17/1/12.
 * 返回快照信息
 */
public class JsonUtil {
    /**
     * 得到code
     *
     * @param resBoby
     * @return
     */
    public static ResponseVo getResponseVo(String resBoby) {
        if (resBoby == null | "".equals(resBoby)) {
            return null;
        }
        Gson gson = new Gson();
        ResponseVo OrderData = gson.fromJson(resBoby, ResponseVo.class);//json转Resvo取到data
        return OrderData;
    }

    /**
     * 得到data中的数据转换成class
     *
     * @param resBoby
     * @return
     */
    public static SvcOrder getSvcOrder(String resBoby) {
        if (resBoby == null | "".equals(resBoby)) {
            return null;
        }
        Gson gson = new Gson();
        ResponseVo OrderData = gson.fromJson(resBoby, ResponseVo.class);//json转Resvo取到data
        String xx = gson.toJson(OrderData.getData());//data转json
        SvcOrder sv = gson.fromJson(xx, SvcOrder.class);//json装Data
        return sv;
    }
}
