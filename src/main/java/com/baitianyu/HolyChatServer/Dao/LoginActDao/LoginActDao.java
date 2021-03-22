package com.baitianyu.HolyChatServer.Dao.LoginActDao;

import com.baitianyu.HolyChatServer.Entity.LoginInfo;
import com.baitianyu.HolyChatServer.Entity.UserInfo;

public interface LoginActDao {
    int Login(LoginInfo loginInfo);
    int Register(UserInfo userInfo);
    int FindPsw(LoginInfo loginInfo);//找回密码
    int User_IsExist(String account);
    //找该用户名的密保问题和密保答案
    UserInfo FindSecurityQuestion_Ans(String account);
    String ReturnUserName(String account);//获取用户名，用来显示出来
    
}
