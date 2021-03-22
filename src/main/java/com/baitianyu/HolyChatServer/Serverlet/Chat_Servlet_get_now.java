package com.baitianyu.HolyChatServer.Serverlet;

import com.alibaba.fastjson.JSON;
import com.baitianyu.HolyChatServer.Entity.Chat;
import com.baitianyu.HolyChatServer.Service.MainActSer.ChatActSer;
import com.baitianyu.HolyChatServer.State.URLParameterTpye;
import com.baitianyu.HolyChatServer.Utils.HTTPUtils;
import com.baitianyu.HolyChatServer.Utils.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@WebServlet(name = "Chat_Servlet_get_now", value = "/Chat_Servlet_get_now")
public class Chat_Servlet_get_now extends HttpServlet {
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
            ArrayList<Chat> chatArrayList= chatActSer.ChatGetSer(account,friend_account);
            if (chatArrayList.size()==0)//如果数据库查询来新的信息为空的话就不去调用这个ai对话了
            { }else {
                /*Chat lastchat=chatArrayList.get(chatArrayList.size()-1);
                String lastcontent= URLEncoder.encode(lastchat.getMessage_content(),"UTF-8");
                URL url = new URL("http://api.qingyunke.com/api.php?key=free&appid=0&msg="+lastcontent);
                HttpURLConnection connection= HTTPUtils.geturlconnection(url,"GET");
                InputStream inputStream=connection.getInputStream();
                String get= StreamUtils.GetStringFromServer(inputStream);
                System.out.println("get="+get);*/
            }
            String chatlistJson= JSON.toJSONString(chatArrayList);
            for (int i=0;i<chatArrayList.size();i++)
            {
                if (chatArrayList.get(i).getAccount()!=null)
                {
                    System.out.println("chat_serverlet_get_now type="+type);
                    System.out.println(chatArrayList.get(i).toString());
                }
            }
            outputStream.write(chatlistJson.getBytes(StandardCharsets.UTF_8));
        }
    }
}
