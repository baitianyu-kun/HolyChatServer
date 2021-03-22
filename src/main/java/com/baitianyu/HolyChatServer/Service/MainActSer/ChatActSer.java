package com.baitianyu.HolyChatServer.Service.MainActSer;

import com.baitianyu.HolyChatServer.DaoImpl.MainActDaoImpl.ChatActDaoImpl;
import com.baitianyu.HolyChatServer.Entity.Chat;
import com.baitianyu.HolyChatServer.State.Status;

import java.util.ArrayList;

public class ChatActSer {
    private ChatActDaoImpl chatActDaoImpl=new ChatActDaoImpl();
    public int ChatSendSer(Chat chat)
    {
        if (chatActDaoImpl.ChatSend(chat)== Status.CHAT_SEND_SUCCESS)
            return Status.CHAT_SEND_SUCCESS;
        else
            return Status.CHAT_SEND_FAILED;
    }
    public ArrayList<Chat> ChatGetSer(String account,String friend_account)
    {
        return chatActDaoImpl.ChatGet(account,friend_account);
    }
    public Chat ChatGetSer2(String account,String friend_account)
    {
        return chatActDaoImpl.ChatGet2(account,friend_account);
    }
    public ArrayList<Chat> ChatGetBefore(String account, String friend_account)
    {
        return chatActDaoImpl.ChatGetBefore(account,friend_account);
    }
}
