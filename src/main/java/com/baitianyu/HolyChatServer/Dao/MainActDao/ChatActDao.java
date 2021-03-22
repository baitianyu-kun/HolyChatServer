package com.baitianyu.HolyChatServer.Dao.MainActDao;

import com.baitianyu.HolyChatServer.Entity.Chat;

import java.util.ArrayList;

public interface ChatActDao {
    int ChatSend(Chat chat);//消息发送
    ArrayList<Chat> ChatGet(String account,String friend_account);//接收实时信息
    ArrayList<Chat> ChatGetBefore(String account,String friend_account);//找到以前的信息

}
