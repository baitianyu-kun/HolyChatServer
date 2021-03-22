package com.baitianyu.HolyChatServer.DaoImpl.MainActDaoImpl;

import com.baitianyu.HolyChatServer.Dao.MainActDao.ChatActDao;
import com.baitianyu.HolyChatServer.Entity.Chat;
import com.baitianyu.HolyChatServer.State.Status;
import com.baitianyu.HolyChatServer.Utils.DBUtils;

import javax.print.DocFlavor;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class ChatActDaoImpl implements ChatActDao {
    //private Connection connection;
    //private PreparedStatement preparedStatement;
    //public ResultSet resultSet;
    /*
    单元测试
     */
   public static void main(String[] args) {
        //int x=new ChatActDaoImpl().ChatSend(new Chat("123456","123457","你好我是123456","2020-01-14 21:24:27"));
       //System.out.println(x);
        /*ArrayList<Chat>chats=new ChatActDaoImpl().ChatGetBefore("123457","123456");
        for (int i=0;i<chats.size();i++)
        {
            Chat chat=chats.get(i);
            System.out.println(chat.toString());
        }*/
       ArrayList<Chat>chatArrayList=new ChatActDaoImpl().ChatGet("1234567","123456");
       for (int i=0;i< chatArrayList.size();i++)
       {
           Chat chat= chatArrayList.get(i);
           System.out.println(chat.toString());
       }
       //Chat chat=new ChatActDaoImpl().ChatGet2("123456","123457");
       //System.out.println(chat.toString());
    }
    @Override
    public int ChatSend(Chat chat) {
        try {
            Connection connection_send= DBUtils.getConnection();
            String sql="insert into unread_list(Account,ToAccount,Message_content,Send_Time,Message_isRead) values (?,?,?,?,'false');";
            String sql2="lock tables unread_list write;";
            String sql3="unlock tables;";
            PreparedStatement preparedStatement_send= connection_send.prepareStatement(sql);
            preparedStatement_send.setString(1, chat.getAccount());
            preparedStatement_send.setString(2,chat.getToAccount());
            preparedStatement_send.setString(3,chat.getMessage_content());
            preparedStatement_send.setString(4,chat.getSend_Time());
            int x=preparedStatement_send.executeUpdate();
            connection_send.close();
            preparedStatement_send.close();
            if (x!=0)
                return Status.CHAT_SEND_SUCCESS;
            else
                return Status.CHAT_SEND_FAILED;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return Status.CHAT_SEND_FAILED;
        }
    }
    public Chat ChatGet2(String account,String friend_account)
    {

        /*Chat chat = new Chat();
        try {
            Connection connection = DBUtils.getConnection();
            String sql = "select * from unread_list where ToAccount=" + account + " and Account = " + friend_account + " order by Send_Time;";
            //System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chat.setAccount(resultSet.getString("Account"));
                chat.setToAccount(resultSet.getString("ToAccount"));
                chat.setMessage_content(resultSet.getString("Message_content"));
                chat.setSend_Time(resultSet.getString("Send_Time"));
                chat.setMessage_isRead(resultSet.getString("Message_isRead"));

                String sql2 = "insert ignore into readed_list(Account,ToAccount,Message_content,Send_Time,Message_isRead) values (?,?,?,?,'true')";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setString(1, chat.getAccount());
                preparedStatement2.setString(2, chat.getToAccount());
                preparedStatement2.setString(3, chat.getMessage_content());
                preparedStatement2.setString(4, chat.getSend_Time());
                preparedStatement2.executeUpdate();//移动到已读当中

                String sql3 = "delete from unread_list where ToAccount=" + account + " and Account=" + friend_account + ";";
                PreparedStatement preparedStatement3= connection.prepareStatement(sql3);
                preparedStatement3.executeUpdate();//从未读中删除

            }
        }catch (SQLException e)

        {
            e.printStackTrace();
        }
        return chat;*/
        return null;
    }
    @Override
    public ArrayList<Chat> ChatGet(String account,String friend_account) {
        ArrayList<Chat>chats=new ArrayList<>();
        try {
            Connection connection_get=DBUtils.getConnection();
            String sql="select * from unread_list where ToAccount="+account+" and Account = "+friend_account+" order by Send_Time;";
            //System.out.println(sql);
            PreparedStatement preparedStatement_get= connection_get.prepareStatement(sql);
            ResultSet resultSet_get= preparedStatement_get.executeQuery();
            while (resultSet_get.next())
            {
                Chat chat=new Chat();
                chat.setAccount(resultSet_get.getString("Account"));
                chat.setToAccount(resultSet_get.getString("ToAccount"));
                chat.setMessage_content(resultSet_get.getString("Message_content"));
                chat.setSend_Time(resultSet_get.getString("Send_Time"));
                chat.setMessage_isRead(resultSet_get.getString("Message_isRead"));
                chats.add(chat);

                Connection connection2=DBUtils.getConnection();
                String sql2="insert ignore into readed_list(Account,ToAccount,Message_content,Send_Time,Message_isRead) values (?,?,?,?,'true')";
                PreparedStatement preparedStatement2= connection2.prepareStatement(sql2);
                preparedStatement2.setString(1,chat.getAccount());
                preparedStatement2.setString(2, chat.getToAccount());
                preparedStatement2.setString(3,chat.getMessage_content());
                preparedStatement2.setString(4, chat.getSend_Time());
                preparedStatement2.executeUpdate();//移动到已读当中
                preparedStatement2.close();

                Connection connection3=DBUtils.getConnection();
                String sql3="delete from unread_list where ToAccount="+account+" and Account="+friend_account+";";
                PreparedStatement preparedStatement3= connection3.prepareStatement(sql3);
                preparedStatement3.executeUpdate();//从未读中删除
                preparedStatement3.close();

            }
            connection_get.close();
            preparedStatement_get.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        //System.out.println("size="+chats.size());
        return chats;
    }

    @Override
    public ArrayList<Chat> ChatGetBefore(String account, String friend_account) {
        ArrayList<Chat> chatArrayList=new ArrayList<>();
        try {
            Connection connection_get_before=DBUtils.getConnection();
            String sql="select * from readed_list where ToAccount="+account+" and Account = "+friend_account+" or ToAccount= "+friend_account+" and Account="+account+" order by Send_Time;";
            PreparedStatement preparedStatement_get_before=connection_get_before.prepareStatement(sql);
            ResultSet resultSet_get_before=preparedStatement_get_before.executeQuery();
            while (resultSet_get_before.next())
            {
                Chat chat=new Chat();
                chat.setAccount(resultSet_get_before.getString("Account"));
                chat.setToAccount(resultSet_get_before.getString("ToAccount"));
                chat.setMessage_content(resultSet_get_before.getString("Message_content"));
                chat.setSend_Time(resultSet_get_before.getString("Send_Time"));
                chat.setMessage_isRead(resultSet_get_before.getString("Message_isRead"));
                chatArrayList.add(chat);
            }
            connection_get_before.close();
            resultSet_get_before.close();
            preparedStatement_get_before.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return chatArrayList;
    }

}
