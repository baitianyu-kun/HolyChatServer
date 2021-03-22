package com.baitianyu.HolyChatServer.Entity;

public class LoginInfo {
    private String account;
    private String password;
    private String new_password;//新设置的密码
    public LoginInfo() {
    }

    public LoginInfo(String account, String password, String new_password) {
        this.account = account;
        this.password = password;
        this.new_password = new_password;
    }

    public LoginInfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
