package com.red.figureapi.controller;

import com.red.figureapi.service.TotalDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.red.figureapi.utils.WebSocketUtils.*;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 15:09 2022-08-08
 */
@Slf4j
@RestController
@ServerEndpoint("/websocket/searchTermNum")
public class WebSocketServer {

    private static TotalDataService totalDataService;

    @Autowired
    public void WebSocketServer(TotalDataService totalDataService) {
        WebSocketServer.totalDataService = totalDataService;
    }

    @OnOpen
    public void openSession(Session session) {
        // 获取当前连接的sessionID
        String sessionID = session.getId();

        // 保存当前连接
        ONLINE_USER_SESSIONS.put(sessionID, session);

        // 建立连接成功并发送通知
        sendMessage(session, "建立WebSocket连接成功！您的sessionID=" + sessionID);

        sendCurrentOnlineUsersSizeMsg();

        System.out.println("建立连接成功！sessionID=" + sessionID);
    }


    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("接收到消息：" + message);

        try {
            String jsonObject = null;
            //转换成json字符串
            if (("searchTermNum").equals(message)) {
                jsonObject = toJSONString(totalDataService.searchTermNum());
            } else if (("searchAmtDistribution").equals(message)) {
                jsonObject = toJSONString(totalDataService.searchAmtDistribution());
            } else if (("searchProvinceNum").equals(message)) {
                jsonObject = toJSONString(totalDataService.searchProvinceNum());
            } else if (("searchPurposeNum").equals(message)) {
                jsonObject = toJSONString(totalDataService.searchPurposeNum());
            } else if (("searchIntRateDistributionOfLoanStatus").equals(message)) {
                jsonObject = toJSONString(totalDataService.searchIntRateDistributionOfLoanStatus());
            }
            // 给客户端发送消息
            sendMessage(session, jsonObject);
        } catch (Exception e) {
            log.warn("get data failed, message is: {}, e: {}", message, e);
        }
    }

    @OnClose
    public void onClose(Session session) {
        // 当前的Session移除
        ONLINE_USER_SESSIONS.remove(session.getId());

        // 通知当前在线人数
        sendCurrentOnlineUsersSizeMsg();

        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("已断开连接！");
    }


    @OnError
    public void onError(Throwable throwable) {
        System.out.println("报错了。错误信息：" + throwable.getMessage());
    }


    // 通知当前在线人数
    public void sendCurrentOnlineUsersSizeMsg() {
        sendMessageAll("当前在线人数：" + ONLINE_USER_SESSIONS.size());
    }
}
