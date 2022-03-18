package com.msb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test02 {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    ;
    private static String user = "root";
    private static String password = "root";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        Connection connection=null;
        Statement statement=null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user,password);
            statement = connection.createStatement();
            String sql="insert into dept values( ,'助教部门','北京')";
            String sql2 = "insert into account values(null,'老北极',2999)";
            int rows = statement.executeUpdate(sql2);
            System.out.println("影响数据行数为:"+rows);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != statement){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
