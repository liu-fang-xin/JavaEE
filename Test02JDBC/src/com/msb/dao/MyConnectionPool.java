package com.msb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyConnectionPool {
    private static String driver ="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String user="root";
    private static String password="root";

    private static int initSize=1;
    private static int maxSize=1;

    private static LinkedList<Connection> pool;

    static{
        // 加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 初始化pool
        pool=new LinkedList<Connection>();
        // 创建5个链接对象
        for (int i = 0; i <initSize ; i++) {
            Connection connection = initConnection();
            if(null != connection){
                pool.add(connection);
                System.out.println("初始化连接"+connection.hashCode()+"放入连接池");
            }
        }
    }
    // 私有的初始化一个链接对象的方法
    private static Connection initConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 共有的向外界提供链接对象的
    public static Connection getConnection(){
        Connection connection =null;
        if(pool.size()>0){
            connection= pool.removeFirst();// 移除集合中的第一个元素
            System.out.println("连接池中还有连接:"+connection.hashCode());
        }else{
            connection = initConnection();
            System.out.println("连接池空,创建新连接:"+connection.hashCode());
        }
        return connection;
    }
    // 共有的向连接池归还连接对象的方法
    public static void returnConnection(Connection connection){
        if(null != connection){
            try {
                if(!connection.isClosed()){
                    if(pool.size()<maxSize){
                        try {
                            connection.setAutoCommit(true);// 调整事务状态
                            System.out.println("设置连接:"+connection.hashCode()+"自动提交为true");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        pool.addLast(connection);
                        System.out.println("连接池未满,归还连接:"+connection.hashCode());
                    }else{
                        try {
                            connection.close();
                            System.out.println("连接池满了,关闭连接:"+connection.hashCode());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    System.out.println("连接:"+connection.hashCode()+"已经关闭,无需归还");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("传入的连接为null,不可归还");
        }
    }
}
