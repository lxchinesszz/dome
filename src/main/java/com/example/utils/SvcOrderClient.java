package com.example.utils;

import com.example.domain.Order;
import com.google.gson.Gson;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http连接池，http请求会经过三次握手，四次挥手，每请求一次握手挥手的时间是很浪费的，
 * 自高访问量的情况下会很容易出现严重错误，使用连接池的技术，大大避免了时间的浪费
 * 对SvcOrder保存订单方法进行，封装
 * 使用方法：
 * SvcOrderClient svcOrderClient=new SvcOrderClient();
 * svcOrderClient.setUrl("...").reflush();
 * 可以配置自己的连接池和伪装ip
 */
public class SvcOrderClient {
    /**
     * 日志处理类
     */
    private static final Logger log = LoggerFactory.getLogger(SvcOrderClient.class);
    // 读取超时
    private final static int SOCKET_TIMEOUT = 10000;
    // 连接超时
    private final static int CONNECTION_TIMEOUT = 10000;
    // 每个HOST的最大连接数量
    private final static int MAX_CONN_PRE_HOST = 20;
    // 连接池的最大连接数
    private final static int MAX_CONN = 60;
    // 连接池
    private final static HttpConnectionManager httpConnectionManager;

    private final static Header header;
    //是否开启
    private final static boolean flag = false;
    //请求头
    private List<Header> headers;

    private static HttpClient httpClient = new HttpClient();

    private static String baseurl;

    private final static Gson gson = new Gson();

    public final static String REQUEST_HEADER = "x-forwarded-for";

    static {
        httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(CONNECTION_TIMEOUT);
        params.setSoTimeout(SOCKET_TIMEOUT);
        params.setDefaultMaxConnectionsPerHost(MAX_CONN_PRE_HOST);
        params.setMaxTotalConnections(MAX_CONN);
        header = new Header();
        header.setName("Content-Type");
        header.setValue("application/json;charset=utf-8");
        httpClient.setHttpConnectionManager(httpConnectionManager);
    }

    /**
     * 允许用户自定义连接器，和参数方法
     *
     * @param connectionManager
     * @return
     */
    public SvcOrderClient setHttpConnecttionManager(HttpConnectionManager connectionManager) {
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(CONNECTION_TIMEOUT);
        params.setSoTimeout(SOCKET_TIMEOUT);
        params.setDefaultMaxConnectionsPerHost(MAX_CONN_PRE_HOST);
        params.setMaxTotalConnections(MAX_CONN);
        Header header = new Header();
        header.setName("Content-Type");
        header.setValue("application/json;charset=utf-8");
        httpClient.setHttpConnectionManager(connectionManager);
        return this;
    }


    /**
     * 空方法，保持代码的逻辑性
     *
     * @return
     */
    public SvcOrderClient refresh() {
        return this;
    }

    /**
     * 设置伪装ip
     * 这个设置可以伪装IP请求,注意使用
     *
     * @param ip
     * @return
     */
    public SvcOrderClient setPretendIp(String ip) {
        headers.add(new Header(REQUEST_HEADER, ip));
        httpClient.getHostConfiguration().getParams().setParameter(
                "http.default-headers", headers);
        return this;
    }

    /**
     * 设置SvcOrder请求地址
     *
     * @param url
     * @return
     */
    public SvcOrderClient setUrl(String url) {
        baseurl = url;
        return this;
    }

    /**
     * 发送主要方法,异常捕获
     * 配合JsonUtil使用，JsonUtil工具为liuxin提前封装过的方法，在Svc_shenghuojiaofei 工具包中可以找到
     * ResponseVo OrderData = JsonUtil.getResponseVo(resBoby);
     * SvcOrder svcOrder = JsonUtil.getSvcOrder(resBoby);
     *
     * @param order
     * @param orderStatus 订单状态
     * @return
     */
    public String saveOrderToSvc(Order order, OrderStatus orderStatus) {
        PostMethod postMethod = new PostMethod(baseurl + "?orderType=" + order.getOrderType());
        BufferedReader in = null;
        String resultString ="";
        Map<String, Object> postMap = new HashedMap();
        order.setOrderType("shenghuojiaofei");
        postMap.put("doc", order);
        Map<String, NextStatus> nextStatuss = new HashedMap();
        NextStatus next = new NextStatus(OrderStatus.be_paid);
        nextStatuss.put("nextStatus", next);
        postMap.put("opts", nextStatuss);
        postMethod.setRequestHeader(header);
        postMethod.setRequestBody(gson.toJson(postMap));
        log.debug("下订单请求参数:{}", gson.toJson(postMap));
        try {
            httpClient.executeMethod(postMethod);
            in = new BufferedReader(new InputStreamReader(postMethod
                    .getResponseBodyAsStream()));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            resultString = buffer.toString();
        } catch (SocketTimeoutException e) {
            log.error("连接超时" + e.toString());
        } catch (HttpException e) {
            log.error("读取外部服务器数据失败" + e.toString());
        } catch (UnknownHostException e) {
            log.error("请求的主机地址无效" + e.toString());
        } catch (IOException e) {
            log.error("向外部接口发送数据失败" + e.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            postMethod.releaseConnection();
            //手动清理对象
            postMap = null;
            nextStatuss = null;
            next = null;
        }
        return resultString;
    }


    /**
     * 修改状态
     *
     * @param orderType 服务品类名称 eg.shenghuojiaofei
     * @param id        订单号
     * @param orderStatus  订单状态
     */
    @SuppressWarnings("deprecation")
    public String updateOrderStatus(String orderType, String id, OrderStatus... orderStatus) {
        String resultString ="";
        PutMethod putMethod = new PutMethod(baseurl + "?orderType=" + orderType + "&id=" + id);
        putMethod.addRequestHeader("Content-Type", "application/json;charset=utf-8");
        Map<String, Object> paidMap = new HashedMap();
        Map<String, Object> docMap = new HashedMap();
        ArrayList<NextStatus> statuses = new ArrayList<>();
        BufferedReader in = null;
        docMap.put("canCancel", 0);//设置状态为不可退款
        for (OrderStatus os:orderStatus){
            statuses.add(new NextStatus(os));
        }
        paidMap.put("statuses", statuses);
        paidMap.put("opts", docMap);
        String requestjson = gson.toJson(paidMap);
        log.debug("修改状态请求参数:{}", requestjson);
        putMethod.setRequestBody(requestjson);
        try {
            httpClient.executeMethod(putMethod);
            in = new BufferedReader(new InputStreamReader(putMethod
                    .getResponseBodyAsStream()));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            resultString = buffer.toString();
        } catch (SocketTimeoutException e) {
            log.error("连接超时" + e.toString());
        } catch (HttpException e) {
            log.error("读取外部服务器数据失败" + e.toString());
        } catch (UnknownHostException e) {
            log.error("请求的主机地址无效" + e.toString());
        } catch (IOException e) {
            log.error("向外部接口发送数据失败" + e.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            putMethod.releaseConnection();
            //手动清理对象
            paidMap = null;
            statuses = null;
            docMap = null;
        }
        return resultString;
    }

}