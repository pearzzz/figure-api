package com.red.figureapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.red.figureapi.common.R;
import com.red.figureapi.service.TotalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.OnOpen;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.red.figureapi.utils.WebSocketUtils.*;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 15:09 2022-08-08
 */
@RestController
@ServerEndpoint("/websocket/searchTermNum")
public class WebSocketServer {

    @Autowired
    private TotalDataService totalDataService;

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

        String jsonObject = null;
        if (("searchTermNum").equals(message)) {
            //转换成json字符串
            jsonObject = toJSONString(totalDataService.searchTermNum());
            // 给客户端发送消息
            sendMessage(session, jsonObject);
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
