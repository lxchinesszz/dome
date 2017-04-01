package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Package: com.example.connection
 * @Description: 为每个线程创建一个单独的连接
 * @author: liuxin
 * @date: 17/3/31 下午3:04
 */
public class DBUtil {
    private final static String DRIVER="com.mysql.jdbc.Driver";
    private final static String URL="jdbc:mysql://localhost:3306/demo";
    private final static String USERNAME="root";
    private final static String PASSWORD="root";
    private final static ThreadLocal<Connection>connContainer=new ThreadLocal<Connection>(){
        @Override
        protected Connection initialValue() {
            return null;
        }
    };


    public static Connection getConnection(){
        Connection conn=connContainer.get();
        try {
            if (conn==null){
                Class.forName(DRIVER);
                conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            connContainer.set(conn);
        }
        return conn;
    }

    public static void colseConnection(){
        Connection conn=connContainer.get();
        if (conn!=null){
            try {
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                connContainer.remove();
            }
        }
    }
}
