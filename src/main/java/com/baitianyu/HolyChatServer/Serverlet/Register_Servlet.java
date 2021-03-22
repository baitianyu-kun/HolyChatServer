package com.baitianyu.HolyChatServer.Serverlet;

import com.alibaba.fastjson.JSON;
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

@WebServlet(name = "Register_Servlet", value = "/Register_Servlet")
public class Register_Servlet extends HttpServlet {
    private LoginActSer loginActSer=new LoginActSer();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String register_json=request.getParameter(URLParameterTpye.REGISTER_JSON);//注册请求的json格式字符串
        UserInfo userInfo= JSON.parseObject(register_json,UserInfo.class);
        String getaccount=loginActSer.get_Random_Account_Ser();
        userInfo.setAccount(getaccount);
        System.out.println("register_Serverlet: "+userInfo.toString());
        OutputStream outputStream=response.getOutputStream();
        if (loginActSer.RegisterSer(userInfo)== Status.REGISTER_SUCCESS)
            outputStream.write(getaccount.getBytes(StandardCharsets.UTF_8));//注册成功之后将账号返回
        else
            outputStream.write(null);//注册失败返回null
    }
}
