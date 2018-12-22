package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.QuestionEntity;
import com.gugu.guguuser.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
/**
 * @author ren
 */
@ServerEndpoint("/websocket/{seminarKlassId}/{userId}/{role}")
@Component
public class WebSocketController {
    @Autowired
    QuestionService questionService;
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();
    private Session session;
    Long seminarKlassId;
    Long userId;
    String role;


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("seminarKlassId") Long seminarKlassId,@PathParam("role")String role,@PathParam("userId")Long userId) {
        this.userId=userId;
        this.role=role;
        this.seminarKlassId=seminarKlassId;
        this.session = session;
        webSocketSet.add(this);
        //加入set中
        addOnlineCount();
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            System.out.println("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        //从set中删除
        subOnlineCount();
        //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }


    /**
     * 收到客户端消息后调用的方法
     *1.为获取下一个问题
     * 2.下一个展示
     * 3.结束讨论课
     * @param */
    @OnMessage
    public void onMessage(@PathParam("messageType") Byte messageType,Long attendanceId,Session session) throws IOException {
        System.out.println("收到来自窗口的信息");
        //群发消息
        switch (messageType){
            case 1:
                QuestionEntity questionEntity=questionService.getNext(attendanceId);
                WebSocketController[] webSocketControllers= (WebSocketController[]) webSocketSet.toArray();
                boolean get=false;
                for(int i=0;i<webSocketControllers.length;i++){
                    if(webSocketControllers[i].getUserId().equals(questionEntity.getStudentId())){
                        webSocketControllers[i].sendMessage("请说出你的问题");
                        get=true;
                        break;
                    }
                }
                if(!get){
                    this.sendMessage("系统出现问题");
                }break;
            case 2:
                for(WebSocketController item : webSocketSet){
                    sendMessage("下一个展示");
                }break;
            case 3:
                for(WebSocketController item: webSocketSet){
                    if(item.getRole().equals("ROLE_Student")){
                        sendMessage("讨论课结束");
                    }
                }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText("这是一个message");
    }


    /**
     * 群发自定义消息
     * */
//    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
//        System.out.println("推送消息到窗口"+"，推送内容:"+message);
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                //这里可以设定只推送给这个sid的，为null则全部推送
//                if(sid==null) {
//                    item.sendMessage(message);
//                }else if(item.sid.equals(sid)){
//                    item.sendMessage(message);
//                }
//            } catch (IOException e) {
//                continue;
//            }
//        }
//    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketController.onlineCount--;
    }

    public Long getSeminarKlassId() {
        return seminarKlassId;
    }

    public void setSeminarKlassId(Long seminarKlassId) {
        this.seminarKlassId = seminarKlassId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
