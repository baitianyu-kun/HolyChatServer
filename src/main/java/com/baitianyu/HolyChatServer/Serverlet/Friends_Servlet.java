package com.baitianyu.HolyChatServer.Serverlet;

import com.alibaba.fastjson.JSON;
import com.baitianyu.HolyChatServer.Entity.FriendsList;
import com.baitianyu.HolyChatServer.Entity.UserList;
import com.baitianyu.HolyChatServer.Service.MainActSer.FriendsActSer;
import com.baitianyu.HolyChatServer.State.Status;
import com.baitianyu.HolyChatServer.State.URLParameterTpye;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Stack;

@WebServlet(name = "Friends_Servlet", value = "/Friends_Servlet")
public class Friends_Servlet extends HttpServlet {
    private FriendsActSer friendsActSer=new FriendsActSer();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String account=request.getParameter(URLParameterTpye.ACCOUNT);
        String type=request.getParameter("type");//显示朋友列表？添加朋友？删除好友？
        System.out.println("friends_serverlet:account="+account+" type="+type);
        if (type.equals(URLParameterTpye.SHOW_FRIENDS_LIST))
        {
            OutputStream outputStream=response.getOutputStream();
            ArrayList<FriendsList>friendsLists=friendsActSer.All_Friends_Lists_Ser(account);
            String friendslistsJson= JSON.toJSONString(friendsLists);
            outputStream.write(friendslistsJson.getBytes(StandardCharsets.UTF_8));
        }else if (type.equals(URLParameterTpye.FRIENDS_SEARCH_ACCOUNT))
        {
            OutputStream outputStream=response.getOutputStream();
            String friend_account=request.getParameter(URLParameterTpye.FRIENDS_ACCOUNT);
            ArrayList<UserList>userLists=friendsActSer.FriendSearchByAccountSer(friend_account);
            String userlistsJson=JSON.toJSONString(userLists);
            outputStream.write(userlistsJson.getBytes(StandardCharsets.UTF_8));
        }else if (type.equals(URLParameterTpye.FRIENDS_SEARCH_NAME))
        {
            OutputStream outputStream=response.getOutputStream();
            String friend_name=request.getParameter(URLParameterTpye.FRIENDS_NAME);
            ArrayList<UserList>userLists=friendsActSer.FriendSearchByNameSer(friend_name);
            String userlistsJson=JSON.toJSONString(userLists);
            outputStream.write(userlistsJson.getBytes(StandardCharsets.UTF_8));
        }else if (type.equals(URLParameterTpye.FRIENDS_ADD))
        {
            String friend_list_getJson=request.getParameter(URLParameterTpye.FRIEND_LIST_JSON);
            FriendsList friendsList=JSON.parseObject(friend_list_getJson,FriendsList.class);
            OutputStream outputStream=response.getOutputStream();
            if (friendsActSer.FriendAddSer(friendsList)== Status.FRIEND_ADD_SUCCESS)
                outputStream.write(Status.FRIEND_ADD_SUCCESS);
            else
                outputStream.write(Status.FRIEND_ADD_FAILED);
        }else if (type.equals(URLParameterTpye.FRIENDS_DELETE))
        {
            String friend_list_getJson=request.getParameter(URLParameterTpye.FRIEND_LIST_JSON);
            FriendsList friendsList=JSON.parseObject(friend_list_getJson,FriendsList.class);
            OutputStream outputStream=response.getOutputStream();
            if (friendsActSer.FriendDeleteSer(friendsList)== Status.FRIEND_DELETE_SUCCESS)
                outputStream.write(Status.FRIEND_DELETE_SUCCESS);
            else
                outputStream.write(Status.FRIEND_DELETE_FAILED);
        }
    }
}
