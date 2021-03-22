package com.baitianyu.HolyChatServer.Entity;

public class Chat {
    private String Account;
    private String ToAccount;
    private String Message_content;
    private String Send_Time;
    private String Message_isRead;

    public Chat(String account, String toAccount, String message_content, String send_Time) {
        Account = account;
        ToAccount = toAccount;
        Message_content = message_content;
        Send_Time = send_Time;
    }

    public Chat(String account, String toAccount, String message_content, String send_Time, String message_isRead) {
        Account = account;
        ToAccount = toAccount;
        Message_content = message_content;
        Send_Time = send_Time;
        Message_isRead = message_isRead;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "Account='" + Account + '\'' +
                ", ToAccount='" + ToAccount + '\'' +
                ", Message_content='" + Message_content + '\'' +
                ", Send_Time='" + Send_Time + '\'' +
                ", Message_isRead='" + Message_isRead + '\'' +
                '}';
    }

    public Chat() {
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getToAccount() {
        return ToAccount;
    }

    public void setToAccount(String toAccount) {
        ToAccount = toAccount;
    }

    public String getMessage_content() {
        return Message_content;
    }

    public void setMessage_content(String message_content) {
        Message_content = message_content;
    }

    public String getSend_Time() {
        return Send_Time;
    }

    public void setSend_Time(String send_Time) {
        Send_Time = send_Time;
    }

    public String getMessage_isRead() {
        return Message_isRead;
    }

    public void setMessage_isRead(String message_isRead) {
        Message_isRead = message_isRead;
    }
}
