package com.baitianyu.HolyChatServer.Serverlet;

import com.baitianyu.HolyChatServer.Entity.LoginInfo;
import com.baitianyu.HolyChatServer.Service.LoginActSer.LoginActSer;
import com.baitianyu.HolyChatServer.State.Status;
import com.baitianyu.HolyChatServer.State.URLParameterTpye;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "Login_Servlet", value = "/Login_Servlet")
public class Login_Servlet extends HttpServlet {
    private LoginActSer loginActSer=new LoginActSer();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String account=request.getParameter(URLParameterTpye.ACCOUNT);
        String password=request.getParameter(URLParameterTpye.PASSWORD);
        System.out.println("login_serverlet: "+"account="+account+" password="+password);
        OutputStream outputStream=response.getOutputStream();
        if (loginActSer.LoginSer(new LoginInfo(account,password))== Status.LOGIN_SUCCESS)
        {
            String MyName=loginActSer.ReturnUserName(account);
            outputStream.write(MyName.getBytes(StandardCharsets.UTF_8));
        }
        else
        {
            outputStream.write(Status.LOGIN_FAILED_String.getBytes(StandardCharsets.UTF_8));
        }
    }
}
