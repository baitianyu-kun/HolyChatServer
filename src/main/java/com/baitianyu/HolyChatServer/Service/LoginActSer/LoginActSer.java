package com.baitianyu.HolyChatServer.Service.LoginActSer;

import com.baitianyu.HolyChatServer.DaoImpl.LoginActDaoImpl.LoginActDaoImpl;
import com.baitianyu.HolyChatServer.Entity.LoginInfo;
import com.baitianyu.HolyChatServer.Entity.UserInfo;
import com.baitianyu.HolyChatServer.State.Status;

public class LoginActSer {
    private LoginActDaoImpl loginActDao=new LoginActDaoImpl();
    public int LoginSer(LoginInfo loginInfo)
    {
        if (loginActDao.Login(loginInfo)== Status.LOGIN_SUCCESS)
            return Status.LOGIN_SUCCESS;
        else
            return Status.LOGIN_FAILED;
    }
    public String get_Random_Account_Ser()
    {
        return loginActDao.get_Random_Account();
    }
    public int RegisterSer(UserInfo userInfo)
    {
        if (loginActDao.Register(userInfo)==Status.REGISTER_SUCCESS)
            return Status.REGISTER_SUCCESS;
        else
            return Status.REGISTER_FAILED;
    }
    public int FindPswSer(LoginInfo loginInfo)
    {
        if (loginActDao.FindPsw(loginInfo)==Status.PASSWORD_CHANGE_SUCCESS)
            return Status.PASSWORD_CHANGE_SUCCESS;
        else
            return Status.PASSWORD_CHANGE_FAILED;
    }
    public UserInfo FindSecurityQuestion_Ans_Ser(String account)
    {
        return loginActDao.FindSecurityQuestion_Ans(account);
    }
    public String ReturnUserName(String account)
    {
        return loginActDao.ReturnUserName(account);
    }
    public int User_IsExist(String account)
    {
        if (loginActDao.User_IsExist(account)==Status.USER_EXISTED)
            return Status.USER_EXISTED;
        else
            return Status.USER_NOT_EXIST;
    }
}
