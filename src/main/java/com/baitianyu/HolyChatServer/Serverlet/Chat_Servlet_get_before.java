package com.baitianyu.HolyChatServer.Serverlet;

import com.alibaba.fastjson.JSON;
import com.baitianyu.HolyChatServer.Entity.Chat;
import com.baitianyu.HolyChatServer.Service.MainActSer.ChatActSer;
import com.baitianyu.HolyChatServer.State.URLParameterTpye;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@WebServlet(name = "Chat_Servlet_get_before", value = "/Chat_Servlet_get_before")
public class Chat_Servlet_get_before extends HttpServlet {
    private ChatActSer chatActSer=new ChatActSer();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type=request.getParameter("type");
        System.out.println("chat_serverlet_get_before: type="+type);
        if (type.equals(URLParameterTpye.CHAT_GET_BEFORE))
        {
            String account=request.getParameter(URLParameterTpye.ACCOUNT);
            String friend_account=request.getParameter(URLParameterTpye.FRIENDS_ACCOUNT);
            OutputStream outputStream=response.getOutputStream();
            ArrayList<Chat> chatArrayList= chatActSer.ChatGetBefore(account,friend_account);
            String chatlistJson= JSON.toJSONString(chatArrayList);
            //System.out.println(account+friend_account);
            //System.out.println(chatlistJson);
            outputStream.write(chatlistJson.getBytes(StandardCharsets.UTF_8));
        }
    }
}
