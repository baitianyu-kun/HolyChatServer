package com.baitianyu.HolyChatServer.DaoImpl.MainActDaoImpl;

import com.baitianyu.HolyChatServer.Dao.MainActDao.FriendsActDao;
import com.baitianyu.HolyChatServer.DaoImpl.LoginActDaoImpl.LoginActDaoImpl;
import com.baitianyu.HolyChatServer.Entity.FriendsList;
import com.baitianyu.HolyChatServer.Entity.UserList;
import com.baitianyu.HolyChatServer.State.Status;
import com.baitianyu.HolyChatServer.Utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FriendsActDaoImpl implements FriendsActDao {

    /*
    单元测试
     */
    public static void main(String[] args) {
        /*ArrayList<UserList>userLists=new FriendsActDaoImpl().FriendSearchByAccount("123456");
        for (int i=0;i<userLists.size();i++)
        {
            UserList userList=userLists.get(i);
            System.out.println(userList.getUserAccount()+" "+userList.getUserName());
        }*/
        /*ArrayList<UserList>userLists=new FriendsActDaoImpl().FriendSearchByName("en");
        for (int i=0;i<userLists.size();i++)
        {
            UserList userList=userLists.get(i);
            System.out.println(userList.getUserAccount()+" "+userList.getUserName());
        }*/

        //System.out.println(new FriendsActDaoImpl().FriendAdd(new FriendsList("123456","456789","pengyou")));
        //System.out.println(new FriendsActDaoImpl().FriendDelete(new FriendsList("123456","456789")));

        /*ArrayList<FriendsList>friendsLists=new FriendsActDaoImpl().All_Friends_Lists("123456");
        for (int i=0;i<friendsLists.size();i++)
        {
            FriendsList friendsList= friendsLists.get(i);
            System.out.println("主人="+friendsList.getAccount()
            +" 好友名称="+friendsList.getFriends_Name()+" 好友账号="+friendsList.getFriends_Account());
        }*/

        System.out.println(new FriendsActDaoImpl().FriendDelete(new FriendsList("123456","456789")));

    }

    @Override
    public ArrayList<UserList> FriendSearchByAccount(String Friend_Account) {
        ArrayList<UserList> userLists=new ArrayList<>();
        try {
            Connection connection_search= DBUtils.getConnection();
            String sql="select * from user_list where User_Account="+Friend_Account+";";
            PreparedStatement preparedStatement_search= connection_search.prepareStatement(sql);
            ResultSet resultSet_search=preparedStatement_search.executeQuery();
            while (resultSet_search.next())
            {
                UserList userList=new UserList(
                        resultSet_search.getString("User_Name"),
                        resultSet_search.getString("User_Account")
                );
                userLists.add(userList);
            }
            DBUtils.closeConn(connection_search,preparedStatement_search,resultSet_search);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return userLists;
    }

    @Override
    public ArrayList<UserList> FriendSearchByName(String Friend_Name) {
        ArrayList<UserList> userLists=new ArrayList<>();
        try {
            Connection connection_search= DBUtils.getConnection();
            String sql="select * from user_list where User_Name='"+Friend_Name+"';";
            PreparedStatement preparedStatement_search= connection_search.prepareStatement(sql);
            ResultSet resultSet_search=preparedStatement_search.executeQuery();
            while (resultSet_search.next())
            {
                UserList userList=new UserList(
                        resultSet_search.getString("User_Name"),
                        resultSet_search.getString("User_Account")
                );
                userLists.add(userList);
            }
            DBUtils.closeConn(connection_search,preparedStatement_search,resultSet_search);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return userLists;
    }

    @Override
    public int FriendAdd(FriendsList friendsList) {//我添加他的同时把我也添加到他那里面哈哈哈哈啊哈哈
        try {
            Connection connection_add=DBUtils.getConnection();
            String sql="insert into friends_list(Account,Friends_Account,Friends_Name) values (?,?,?);";
            PreparedStatement preparedStatement_add= connection_add.prepareStatement(sql);
            preparedStatement_add.setString(1,friendsList.getAccount());
            preparedStatement_add.setString(2, friendsList.getFriends_Account());
            preparedStatement_add.setString(3, friendsList.getFriends_Name());
            preparedStatement_add.executeUpdate();
            String sql1="insert into friends_list(Account,Friends_Account,Friends_Name) values (?,?,?);";
            Connection connection2=DBUtils.getConnection();
            PreparedStatement preparedStatement2=connection2.prepareStatement(sql1);
            preparedStatement2.setString(1,friendsList.getFriends_Account());
            preparedStatement2.setString(2,friendsList.getAccount());
            preparedStatement2.setString(3,new LoginActDaoImpl().ReturnUserName(friendsList.getAccount()));

            if (preparedStatement2.executeUpdate()!=0)
            {
                connection_add.close();
                connection2.close();
                preparedStatement_add.close();
                preparedStatement2.close();
                return Status.FRIEND_ADD_SUCCESS;
            }
            else {
                connection_add.close();
                connection2.close();
                preparedStatement_add.close();
                preparedStatement2.close();
                return Status.FRIEND_ADD_FAILED;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            return Status.FRIEND_ADD_FAILED;
        }
    }

    @Override
    public int FriendDelete(FriendsList friendsList) {
        try {
            Connection connection_delete=DBUtils.getConnection();
            String sql="delete from friends_list where Account="+friendsList.getAccount()+" and Friends_Account="+friendsList.getFriends_Account()+";";
            PreparedStatement preparedStatement_delete= connection_delete.prepareStatement(sql);
            if (preparedStatement_delete.executeUpdate()!=0)
            {
                DBUtils.closeConn(connection_delete,preparedStatement_delete);
                //把我也从对方列表中删除，是不是很爽
                String sql2="delete from friends_list where Account="+friendsList.getFriends_Account()+" and Friends_Account="+friendsList.getAccount()+";";

                Connection delete_others=DBUtils.getConnection();
                PreparedStatement delete_others_pre=delete_others.prepareStatement(sql2);
                delete_others_pre.executeUpdate();
                DBUtils.closeConn(delete_others,delete_others_pre);
                return Status.FRIEND_DELETE_SUCCESS;
            }
            else
            {
                DBUtils.closeConn(connection_delete,preparedStatement_delete);
                return Status.FRIEND_DELETE_FAILED;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            return Status.FRIEND_DELETE_FAILED;
        }
    }

    @Override
    public ArrayList<FriendsList> All_Friends_Lists(String Owner_Account) {
        ArrayList<FriendsList>friendsLists=new ArrayList<>();
        try {
            Connection connection_get_all=DBUtils.getConnection();
            String sql="select Friends_Account,Friends_Name from friends_list where Account="+Owner_Account+";";
            PreparedStatement preparedStatement_get_all= connection_get_all.prepareStatement(sql);
            ResultSet resultSet_get_all= preparedStatement_get_all.executeQuery();
            while (resultSet_get_all.next())
            {
                FriendsList friendsList=new FriendsList(Owner_Account,
                        resultSet_get_all.getString("Friends_Account"),
                        resultSet_get_all.getString("Friends_Name"));
                friendsLists.add(friendsList);
            }
            DBUtils.closeConn(connection_get_all,preparedStatement_get_all,resultSet_get_all);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return friendsLists;
    }
}
