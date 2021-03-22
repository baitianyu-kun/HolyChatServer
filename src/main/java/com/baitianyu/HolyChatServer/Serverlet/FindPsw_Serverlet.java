package com.baitianyu.HolyChatServer.Serverlet;

import com.alibaba.fastjson.JSON;
import com.baitianyu.HolyChatServer.Entity.LoginInfo;
import com.baitianyu.HolyChatServer.Entity.UserInfo;
import com.baitianyu.HolyChatServer.Service.LoginActSer.LoginActSer;
import com.baitianyu.HolyChatServer.State.Status;
import com.baitianyu.HolyChatServer.State.URLParameterTpye;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "FindPsw_Serverlet", value = "/FindPsw_Serverlet")
public class FindPsw_Serverlet extends HttpServlet {
    private LoginActSer loginActSer=new LoginActSer();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String account=request.getParameter(URLParameterTpye.ACCOUNT);
        String type=request.getParameter("type");//获得请求类型，即是更新密码？还是获取密保问题和答案？
        System.out.println("findpsw_serverlet: account="+account+" type="+type);
        if (type.equals(URLParameterTpye.GET_SECURITY_QU))//获取密保问题和答案时候
        {
            OutputStream outputStream=response.getOutputStream();
            if (loginActSer.User_IsExist(account)== Status.USER_EXISTED)
            {
                UserInfo userInfo=loginActSer.FindSecurityQuestion_Ans_Ser(account);
                String userInfoJson= JSON.toJSONString(userInfo);
                outputStream.write(userInfoJson.getBytes(StandardCharsets.UTF_8));
            }
            else
            {
                outputStream.write("user_not_exist".getBytes(StandardCharsets.UTF_8));
            }
        }
        else //另外一种情况就是去更新密码
        {
            OutputStream outputStream=response.getOutputStream();
            String password=request.getParameter(URLParameterTpye.PASSWORD);
            LoginInfo loginInfo=new LoginInfo(account,password,password);//账号密码封装成对象，然后这么传给Service层
            if (loginActSer.FindPswSer(loginInfo)==Status.PASSWORD_CHANGE_SUCCESS)
                outputStream.write(Status.PASSWORD_CHANGE_SUCCESS);
            else
                outputStream.write(Status.PASSWORD_CHANGE_FAILED);
        }
    }
}
