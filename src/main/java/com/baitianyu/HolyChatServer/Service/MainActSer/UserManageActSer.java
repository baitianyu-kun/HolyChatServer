package com.baitianyu.HolyChatServer.Service.MainActSer;

import com.baitianyu.HolyChatServer.DaoImpl.MainActDaoImpl.UserManageActDaoImpl;
import com.baitianyu.HolyChatServer.Entity.LoginInfo;
import com.baitianyu.HolyChatServer.State.Status;

public class UserManageActSer {
    private UserManageActDaoImpl userManageActDaoImpl=new UserManageActDaoImpl();
    public int ChangePsw(LoginInfo loginInfo)
    {
        if (userManageActDaoImpl.ChangePsw(loginInfo)== Status.PASSWORD_CHANGE_SUCCESS)
            return Status.PASSWORD_CHANGE_SUCCESS;
        else
            return Status.PASSWORD_CHANGE_FAILED;
    }
}
