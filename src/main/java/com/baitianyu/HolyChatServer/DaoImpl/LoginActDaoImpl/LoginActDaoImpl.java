package com.baitianyu.HolyChatServer.DaoImpl.LoginActDaoImpl;

import com.baitianyu.HolyChatServer.Dao.LoginActDao.LoginActDao;
import com.baitianyu.HolyChatServer.Entity.LoginInfo;
import com.baitianyu.HolyChatServer.Entity.UserInfo;
import com.baitianyu.HolyChatServer.State.Status;
import com.baitianyu.HolyChatServer.Utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class LoginActDaoImpl implements LoginActDao {
    //private Connection connection;
    //private PreparedStatement preparedStatement;
    //public ResultSet resultSet;
    /*
    单元测试
     */
    //public static void main(String[] args) {
        //System.out.println(new LoginActDaoImpl().User_IsExist("123456"));

        /*UserInfo userInfo=new LoginActDaoImpl().FindSecurityQuestion_Ans("123456");
        System.out.println(userInfo.getSecurity_Question()+userInfo.getSecurity_Answer());*/

        //System.out.println(new LoginActDaoImpl().Login(new LoginInfo("123456","123456")));

        //System.out.println(new LoginActDaoImpl().get_Random_Account());

        //System.out.println(new LoginActDaoImpl().Register(new UserInfo("baitianyu","159258","你的好朋友是谁","你爸爸","123456")));

        //System.out.println(new LoginActDaoImpl().AccountEqualsPsw(new LoginInfo("19258","123456")));

        //System.out.println(new LoginActDaoImpl().FindPsw(new LoginInfo("159258","123456","admin123465")));

        //System.out.println(new LoginActDaoImpl().FindPsw(new LoginInfo("123456","admin123555","admin123555")));
    //}

    @Override
    public int Login(LoginInfo loginInfo) {
        if (AccountEqualsPsw(loginInfo)==Status.EQUALS)
            return Status.LOGIN_SUCCESS;
        else
            return Status.LOGIN_FAILED;
    }

    public String get_Random_Account()
    {
        String Account_get_Random;
        Random random=new Random();
        while (true)//随机数来获得账号
        {
            int getnumber=random.nextInt(999999);
            if (getnumber>100000){
                Account_get_Random=String.valueOf(getnumber);//转换成字符串方便操作
                if (User_IsExist(Account_get_Random)==Status.USER_EXISTED){}//判断是否存在这样一个账号，虽然概率很小，但还是得要
                else{break;}
            }
        }
        return Account_get_Random;
    }

    @Override
    public int Register(UserInfo userInfo) {
        try {
            Connection connection_register=DBUtils.getConnection();
            String sql="insert into user_info values (?,?,?,?);";
            PreparedStatement preparedStatement_register= connection_register.prepareStatement(sql);
            preparedStatement_register.setString(1,userInfo.getUser_name());
            preparedStatement_register.setString(2,userInfo.getAccount());
            preparedStatement_register.setString(3,userInfo.getSecurity_Question());
            preparedStatement_register.setString(4,userInfo.getSecurity_Answer());
            if (preparedStatement_register.executeUpdate()!=0)
            {
                String sql2="insert into login_info values (?,?)";
                Connection connection2=DBUtils.getConnection();
                PreparedStatement preparedStatement2= connection2.prepareStatement(sql2);
                preparedStatement2.setString(1,userInfo.getAccount());
                preparedStatement2.setString(2,userInfo.getPassword());
                if (preparedStatement2.executeUpdate()!=0)
                {
                    DBUtils.closeConn(connection_register,preparedStatement_register);
                    DBUtils.closeConn(connection2,preparedStatement2);

                    Connection connection3=DBUtils.getConnection();//写入user_list中
                    String sql3="insert into user_list values (?,?);";
                    PreparedStatement preparedStatement3=connection3.prepareStatement(sql3);
                    preparedStatement3.setString(1, userInfo.getUser_name());
                    preparedStatement3.setString(2,userInfo.getAccount());
                    preparedStatement3.executeUpdate();
                    DBUtils.closeConn(connection3,preparedStatement3);

                    return Status.REGISTER_SUCCESS;
                }
                else{
                    DBUtils.closeConn(connection_register,preparedStatement_register);
                    DBUtils.closeConn(connection2,preparedStatement2);
                    return Status.REGISTER_FAILED;
                }
            }
            else {
                DBUtils.closeConn(connection_register,preparedStatement_register);
                return Status.REGISTER_FAILED;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            return Status.REGISTER_FAILED;
        }
    }

    @Override
    public int FindPsw(LoginInfo loginInfo) {
        try {
            Connection connection_find=DBUtils.getConnection();
            String sql="update login_info set Password='"+loginInfo.getNew_password()+"'where Account="+loginInfo.getAccount()+";";
            PreparedStatement preparedStatement_find= connection_find.prepareStatement(sql);

            if (preparedStatement_find.executeUpdate()!=0)
            {
                DBUtils.closeConn(connection_find,preparedStatement_find);
                return Status.PASSWORD_CHANGE_SUCCESS;
            }
            else
            {
                DBUtils.closeConn(connection_find,preparedStatement_find);
                return Status.PASSWORD_CHANGE_FAILED;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return Status.PASSWORD_CHANGE_FAILED;
        }
    }
    public int AccountEqualsPsw(LoginInfo loginInfo)
    {
        try {
            Connection connection_equals=DBUtils.getConnection();
            String sql="select Password from login_info where Account="+loginInfo.getAccount()+";";
            PreparedStatement preparedStatement_equals= connection_equals.prepareStatement(sql);
            ResultSet resultSet_equals= preparedStatement_equals.executeQuery();
            resultSet_equals.next();
            if (loginInfo.getPassword().equals(resultSet_equals.getString("Password")))
            {
                DBUtils.closeConn(connection_equals,preparedStatement_equals,resultSet_equals);
                return Status.EQUALS;
            }
            else
            {
                DBUtils.closeConn(connection_equals,preparedStatement_equals,resultSet_equals);
                return Status.NOT_EQUALS;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            return Status.NOT_EQUALS;
        }
    }

    @Override
    //找该用户名的密保问题和密保答案
    public UserInfo FindSecurityQuestion_Ans(String account)
    {
        UserInfo userInfo=new UserInfo();
        try {
            Connection connection_find=DBUtils.getConnection();
            String sql="select Security_Question,Security_Answer from user_info where Account="+account+";";
            PreparedStatement preparedStatement_find= connection_find.prepareStatement(sql);
            ResultSet resultSet_find= preparedStatement_find.executeQuery();
            while (resultSet_find.next()) {
                userInfo.setSecurity_Question(resultSet_find.getString("Security_Question"));
                userInfo.setSecurity_Answer(resultSet_find.getString("Security_Answer"));
            }
            DBUtils.closeConn(connection_find,preparedStatement_find,resultSet_find);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return userInfo;
    }
    @Override
    public String ReturnUserName(String account) {
        String username="";
        try {
            Connection connection_return=DBUtils.getConnection();
            String sql="select User_Name from user_info where Account="+account+";";
            PreparedStatement preparedStatement_return= connection_return.prepareStatement(sql);
            ResultSet resultSet_return= preparedStatement_return.executeQuery();
            resultSet_return.next();
            username=resultSet_return.getString("User_Name");
            DBUtils.closeConn(connection_return,preparedStatement_return,resultSet_return);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return username;
    }

    @Override
    public int User_IsExist(String account) {
        try {
            Connection connection_exit= DBUtils.getConnection();
            String sql="select Account from user_info where Account="+account+";";
            PreparedStatement preparedStatement_exit= connection_exit.prepareStatement(sql);
            ResultSet resultSet_exit= preparedStatement_exit.executeQuery();
            if (resultSet_exit.next())
            {
                DBUtils.closeConn(connection_exit,preparedStatement_exit,resultSet_exit);
                return Status.USER_EXISTED;
            }
            else
            {
                DBUtils.closeConn(connection_exit,preparedStatement_exit,resultSet_exit);
                return Status.USER_NOT_EXIST;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            return Status.USER_NOT_EXIST;
        }
    }
}

