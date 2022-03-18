package com.msb;

import com.msb.entity.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test03 {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String user = "root";
    private static String password = "root";
    private static String driver = "com.mysql.cj.jdbc.Driver";


    public static void main(String[] args) {
        List<Emp> list = queryAll();
        System.out.println("list = " + list);
    }


    public static List<Emp> queryAll(){
        Connection connection = null;
        Statement statement = null;
        List<Emp> list = new ArrayList<>();

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            String sql = "select * from emp";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int empno = resultSet.getInt("empno");
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int mgr = resultSet.getInt("mgr");
                Date hiredate = resultSet.getDate("hiredate");
                double sal = resultSet.getDouble("sal");
                double comm = resultSet.getDouble("comm");
                int deptno = resultSet.getInt("deptno");
                Emp emp = new Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno);
                //System.out.println("emp = " + emp.getEname());
                list.add(emp);
                
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
            return list;
        }



    }
}
