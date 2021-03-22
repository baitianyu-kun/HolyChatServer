package com.baitianyu.HolyChatServer.DaoImpl.MainActDaoImpl;

import com.baitianyu.HolyChatServer.Dao.MainActDao.UserManageActDao;
import com.baitianyu.HolyChatServer.DaoImpl.LoginActDaoImpl.LoginActDaoImpl;
import com.baitianyu.HolyChatServer.Entity.LoginInfo;
import com.baitianyu.HolyChatServer.State.Status;

public class UserManageActDaoImpl implements UserManageActDao {
    private LoginActDaoImpl loginActDaoImpl=new LoginActDaoImpl();
/*
单元测试
 */
    //public static void main(String[] args) {
        //new UserManageActDaoImpl().ChangePsw(new LoginInfo("123456","admin123555","woshiniba"));
    //}
    @Override
    public int ChangePsw(LoginInfo loginInfo) {
        if (loginActDaoImpl.AccountEqualsPsw(loginInfo)== Status.EQUALS)
        {
            if (loginActDaoImpl.FindPsw(loginInfo)==Status.PASSWORD_CHANGE_SUCCESS)
                return Status.PASSWORD_CHANGE_SUCCESS;
            else
                return Status.PASSWORD_CHANGE_FAILED;
        }
        else {
            return Status.NOT_EQUALS;//原账号密码不匹配
        }
    }
}
