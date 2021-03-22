package com.baitianyu.HolyChatServer.Serverlet;

import com.alibaba.fastjson.JSON;
import com.baitianyu.HolyChatServer.Entity.Chat;
import com.baitianyu.HolyChatServer.Service.MainActSer.ChatActSer;
import com.baitianyu.HolyChatServer.State.Status;
import com.baitianyu.HolyChatServer.State.URLParameterTpye;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "Chat_Servlet_send", value = "/Chat_Servlet_send")
public class Chat_Servlet_send extends HttpServlet {
    private ChatActSer chatActSer=new ChatActSer();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type=request.getParameter("type");
        System.out.println("chat_serverlet_send: type="+type);
        if (type.equals(URLParameterTpye.CHAT_SEND))
        {
            String chat_sendJSON=request.getParameter(URLParameterTpye.CHAT_SEND_JSON);
            Chat chat= JSON.parseObject(chat_sendJSON,Chat.class);
            OutputStream outputStream=response.getOutputStream();
            System.out.println("send"+chat.toString());
            if (chatActSer.ChatSendSer(chat)== Status.CHAT_SEND_SUCCESS)
                outputStream.write(Status.CHAT_SEND_SUCCESS);
            else
                outputStream.write(Status.CHAT_SEND_FAILED);
        }
    }
}
