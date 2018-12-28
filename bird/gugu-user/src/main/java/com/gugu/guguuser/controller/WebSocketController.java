package com.gugu.guguuser.controller;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ren
 */
@ServerEndpoint("/websocket/{seminarKlassId}/{userId}/{role}")
@Component
public class WebSocketController {
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();
    private Session session;
    Long seminarKlassId;
    Long userId;
    String role;
    Long attendanceId;
    private static Integer questionPeople=0;

    public static Integer getQuestionPeople() {
        return questionPeople;
    }

    public static void setOnlineCount(int onlineCount) {
        WebSocketController.onlineCount = onlineCount;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("seminarKlassId") Long seminarKlassId,@PathParam("role")String role,@PathParam("userId")Long userId) throws IOException, EncodeException {

        this.userId=userId;
        this.role=role;
        this.seminarKlassId=seminarKlassId;
        this.session = session;
        webSocketSet.add(this);
        //加入set中
        addOnlineCount();
        StringBuffer stringBuffer=new StringBuffer("连接成功");
        String attendanceId="";
        if(webSocketSet.size()>0){
            for(WebSocketController webSocketController:webSocketSet){
                try {
                    attendanceId = Long.toString(webSocketController.getAttendanceId());
                    sendMessage(attendanceId);
                    break;
                }catch (NullPointerException e){
                    continue;
                }
            }
            sendMessage("-1");
        }else{
            sendMessage("-1");
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
     *1.下一个展示
     * 2.下一个提问
     * 3.结束讨论课
     * 4.提出问题
     * @param */
    //@PathParam("messageType") Byte messageType,Long attendanceId
    @OnMessage
    public void onMessage(String message,Session session) throws IOException, EncodeException {
        System.out.println(message);
        String[] mes=message.split(";");
        if(mes[0].equals("2")){
            questionPeople--;
            for(WebSocketController webSocketController:webSocketSet){
                    webSocketController.sendMessage("nextQuestion");
            }
        }else if(mes[0].equals("1")){
            questionPeople=0;
            for(WebSocketController webSocketController:webSocketSet){
                if(webSocketController.getRole().equals("ROLE_Student")) {
                    webSocketController.sendMessage("nextPresentation");
                    webSocketController.setAttendanceId(Long.parseLong(mes[1]));
                }
            }
        }else if(mes[0].equals("3")){
            for(WebSocketController webSocketController:webSocketSet){
                if(webSocketController.getRole().equals("ROLE_Student")){
                    webSocketController.sendMessage("end");
                }
            }
        }else if(mes[0].equals("4")){
            questionPeople++;
            for(WebSocketController webSocketController:webSocketSet){
                webSocketController.sendMessage("questionNumber:"+questionPeople);
            }
        }else{
            sendMessage("系统出错");
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("websocket发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    public void sendObject(Object object){

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
