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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@WebServlet(name = "Chat_Servlet", value = "/Chat_Servlet")
public class Chat_Servlet extends HttpServlet {
    private ChatActSer chatActSer=new ChatActSer();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type=request.getParameter("type");
        if (type.equals(URLParameterTpye.CHAT_GET))
        {
            String account=request.getParameter(URLParameterTpye.ACCOUNT);
            String friend_account=request.getParameter(URLParameterTpye.FRIENDS_ACCOUNT);
            OutputStream outputStream=response.getOutputStream();
            Chat chat=chatActSer.ChatGetSer2(account,friend_account);
            //ArrayList<Chat>chatArrayList= chatActSer.ChatGetSer(account,friend_account);
            String chatlistJson= JSON.toJSONString(chat);
            if (chat.getAccount()!=null)
            {
                System.out.println("getChat+"+chat.toString());
            }
            outputStream.write(chatlistJson.getBytes(StandardCharsets.UTF_8));
        }else if (type.equals(URLParameterTpye.CHAT_SEND))
        {
            String chat_sendJSON=request.getParameter(URLParameterTpye.CHAT_SEND_JSON);
            Chat chat=JSON.parseObject(chat_sendJSON,Chat.class);
            OutputStream outputStream=response.getOutputStream();
            System.out.println("sendChat+"+chat.toString());
            if (chatActSer.ChatSendSer(chat)== Status.CHAT_SEND_SUCCESS)
                outputStream.write(Status.CHAT_SEND_SUCCESS);
            else
                outputStream.write(Status.CHAT_SEND_FAILED);
        }else if (type.equals(URLParameterTpye.CHAT_GET_BEFORE))
        {
            String account=request.getParameter(URLParameterTpye.ACCOUNT);
            String friend_account=request.getParameter(URLParameterTpye.FRIENDS_ACCOUNT);
            OutputStream outputStream=response.getOutputStream();
            ArrayList<Chat>chatArrayList= chatActSer.ChatGetBefore(account,friend_account);
            String chatlistJson= JSON.toJSONString(chatArrayList);
            //System.out.println(account+friend_account);
            //System.out.println(chatlistJson);
            outputStream.write(chatlistJson.getBytes(StandardCharsets.UTF_8));
        }

    }
}
