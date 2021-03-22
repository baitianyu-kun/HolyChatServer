package com.baitianyu.HolyChatServer.Entity;

public class UserList {
    private String UserName;
    private String UserAccount;

    public UserList(String userName, String userAccount) {
        UserName = userName;
        UserAccount = userAccount;
    }

    public UserList() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserAccount() {
        return UserAccount;
    }

    public void setUserAccount(String userAccount) {
        UserAccount = userAccount;
    }
}
