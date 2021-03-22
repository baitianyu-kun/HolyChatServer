package com.baitianyu.HolyChatServer.Utils;

import com.baitianyu.HolyChatServer.Entity.Chat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HTTPUtils {
    public static HttpURLConnection geturlconnection(URL url,String get_type)
    {
        HttpURLConnection connection=null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            //设置属性
            connection.setRequestMethod(get_type);
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            //设置输入流和输出流,都设置为true
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Accept", "text/plain, text/html,text/json");//指定客户端能够接收的内容类型
            connection.setRequestProperty("Connection", "keep-alive"); //http1.1
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}
