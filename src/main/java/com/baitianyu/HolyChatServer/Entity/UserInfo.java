package com.baitianyu.HolyChatServer.Entity;

public class UserInfo {
    private String User_name;
    private String Account;
    private String Security_Question;
    private String Security_Answer;
    private String Password;
    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "User_name='" + User_name + '\'' +
                ", Account='" + Account + '\'' +
                ", Security_Question='" + Security_Question + '\'' +
                ", Security_Answer='" + Security_Answer + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public UserInfo(String user_name, String account, String security_Question, String security_Answer, String password) {
        User_name = user_name;
        Account = account;
        Security_Question = security_Question;
        Security_Answer = security_Answer;
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getSecurity_Question() {
        return Security_Question;
    }

    public void setSecurity_Question(String security_Question) {
        Security_Question = security_Question;
    }

    public String getSecurity_Answer() {
        return Security_Answer;
    }

    public void setSecurity_Answer(String security_Answer) {
        Security_Answer = security_Answer;
    }
}
