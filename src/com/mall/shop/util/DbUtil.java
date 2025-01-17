package com.mall.shop.util;

import java.sql.*;

public class DbUtil {
    //properties 文件


    //步骤  加载驱动  获取连接  创建数据处理对象(数据预处理对象) 执行sql 放入rs 遍历rs


    private static String url ="jdbc:mysql://localhost:3306/shoping?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static String uname = "root";
    private static String pwd = "king123456.";

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    DbUtil db = null;

    public static Connection getConn() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //mysql8 以上要使用com.mysql.jc.jdbc.Driver
        Connection conn = DriverManager.getConnection(url,uname,pwd);
        return conn;
    }

    public static PreparedStatement getPstmt(String sql) throws Exception {
        Connection conn= DbUtil.getConn();
        PreparedStatement  pstmt = conn.prepareStatement(sql);
        return pstmt;

    }
    public static PreparedStatement getPstmt2(String sql) throws Exception {
        Connection conn= DbUtil.getConn();
        PreparedStatement  pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        return pstmt;

    }

    public Statement getStmt() throws Exception {
        db = new DbUtil();
        conn = db.getConn();
        stmt = conn.createStatement();
        return stmt;

    }


    public static void close(Connection conn, PreparedStatement pstm, ResultSet rs) {
        try {
            if(conn!=null) {
                conn.close();
            }
            if(pstm!=null) {
                pstm.close();
            }
            if(rs!=null) {
                rs.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void close(Connection conn, PreparedStatement pstm) {
        try {
            if(conn!=null) {
                conn.close();
            }
            if(pstm!=null) {
                pstm.close();
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

