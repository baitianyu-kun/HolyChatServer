package com.baitianyu.HolyChatServer.Service.MainActSer;

import com.baitianyu.HolyChatServer.DaoImpl.MainActDaoImpl.FriendsActDaoImpl;
import com.baitianyu.HolyChatServer.Entity.FriendsList;
import com.baitianyu.HolyChatServer.Entity.UserList;
import com.baitianyu.HolyChatServer.State.Status;

import java.util.ArrayList;

public class FriendsActSer {
    private FriendsActDaoImpl friendsActDaoImpl=new FriendsActDaoImpl();
    public ArrayList<UserList> FriendSearchByAccountSer(String Friend_Account)
    {
        return friendsActDaoImpl.FriendSearchByAccount(Friend_Account);
    }
    public ArrayList<UserList> FriendSearchByNameSer(String Friend_Name)
    {
        return friendsActDaoImpl.FriendSearchByName(Friend_Name);
    }
    public int FriendAddSer(FriendsList friendsList)
    {
        if (friendsActDaoImpl.FriendAdd(friendsList)== Status.FRIEND_ADD_SUCCESS)
            return Status.FRIEND_ADD_SUCCESS;
        else
            return Status.FRIEND_ADD_FAILED;
    }
    public int FriendDeleteSer(FriendsList friendsList)
    {
        if (friendsActDaoImpl.FriendDelete(friendsList)==Status.FRIEND_DELETE_SUCCESS)
            return Status.FRIEND_DELETE_SUCCESS;
        else
            return Status.FRIEND_DELETE_FAILED;
    }
    public ArrayList<FriendsList> All_Friends_Lists_Ser(String Owner_Account)
    {
        return friendsActDaoImpl.All_Friends_Lists(Owner_Account);
    }
    
}
