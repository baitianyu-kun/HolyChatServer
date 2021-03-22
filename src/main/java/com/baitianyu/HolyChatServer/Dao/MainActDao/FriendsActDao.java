package com.baitianyu.HolyChatServer.Dao.MainActDao;

import com.baitianyu.HolyChatServer.Entity.FriendsList;
import com.baitianyu.HolyChatServer.Entity.UserList;

import java.util.ArrayList;

public interface FriendsActDao {
    ArrayList<UserList> FriendSearchByAccount(String Friend_Account);//以账号搜索好友
    ArrayList<UserList> FriendSearchByName(String Friend_Name);//以姓名搜索好友
    int FriendAdd(FriendsList friendsList);//添加好友
    int FriendDelete(FriendsList friendsList);//删除好友
    ArrayList<FriendsList> All_Friends_Lists(String Owner_Account);//通过某一个人的账号返回其所有的好友
}
