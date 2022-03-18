package com.msb;

import java.sql.*;

public class Test01 {
    public static void main(String[] args) throws Exception {
        /*向dept表中增加一条数据*/
        //1.加载驱动
        //Driver driver = new com.mysql.cj.jdbc.Driver();
        //2.注册驱动DriverManger
       // DriverManager.registerDriver(driver);
        /*URL：定位数据库连接地址
        * user：用户名
        * password:密码
        * */
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String user="root";
        String password="root";
        Connection connection = DriverManager.getConnection(url,user,password);
        //获得语句对象（把sql语句传送到 mysql数据库，然后把数据库执行完sql语句的结果 封装在statement里面返回到java）
        Statement statement = connection.createStatement();
        String sql = "insert into dept values(510,'销售部','南昌')";
        int rows = statement.executeUpdate(sql);
        System.out.println("rows = " + rows);

        statement.close();
        connection.close();

    }
}
