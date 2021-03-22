package com.baitianyu.HolyChatServer.Utils;

import java.sql.*;

public class DBUtils {
    private static String DataBase="holychat";
    private static String Aliyun_DataBase="holychat";
    private static String JDBCDRIVER="com.mysql.cj.jdbc.Driver";
    private static String Local_DBURL="jdbc:mysql://localhost:3306/"+DataBase+"?&serverTimezone=UTC";
    private static String Local_user="root";
    private static String Local_password="123456";
    //private static String Aliyun_DBURL="jdbc:mysql://rm-8vb36l9fo0iv60xor588707m.mysql.zhangbei.rds.aliyuncs.com/"+Aliyun_DataBase+"?&serverTimezone=UTC";
    private static String Aliyun_DBURL="jdbc:mysql://rm-8vb36l9fo0iv60xor58870.mysql.zhangbei.rds.aliyuncs.com:3306/"+Aliyun_DataBase+"?&serverTimezone=UTC";

    private static String Aliyun_user="baitianyu_user";
    private static String Aliyun_password="Bai20010129";
    public static Connection getConnection()
    {
        Connection con=null;
        try {
            Class.forName(JDBCDRIVER);
            //con= DriverManager.getConnection(Local_DBURL,Local_user,Local_password);
            con=DriverManager.getConnection(Aliyun_DBURL,Aliyun_user,Aliyun_password);
        }catch (ClassNotFoundException| SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    //关闭连接
    public static void closeConn(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    //关闭连接（无结果集）
    public static void closeConn(Connection conn, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
