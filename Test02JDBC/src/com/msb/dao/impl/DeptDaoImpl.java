package com.msb.dao.impl;

import com.msb.dao.DeptDao;
import com.msb.poji.Dept;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl implements DeptDao {
    private static String driver ="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String user="root";
    private static String password="root";
    @Override
    public List<Dept> findAll() {
        // 查询名字中包含字母A的员工信息
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<Dept> list =null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user,password);
            String sql="select * from dept";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            list=new ArrayList<Dept>() ;
            while(resultSet.next()){
                int deptno = resultSet.getInt("deptno");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");
                Dept dept =new Dept(deptno,dname,loc);
                list.add(dept) ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != resultSet){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != preparedStatement){
                try {
                    preparedStatement.close();
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
        return list;
    }
    @Override
    public int addDept(Dept dept) {
        // 向 Emp表中增加一条数据
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        int rows=0;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user,password);
            String sql="insert into dept values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            preparedStatement.setObject(1,dept.getDeptno());
            preparedStatement.setObject(2,dept.getDname());
            preparedStatement.setObject(3,dept.getLoc() );
            //执行CURD
            rows =preparedStatement.executeUpdate();// 这里不需要再传入SQL语句
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != preparedStatement){
                try {
                    preparedStatement.close();
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
        return rows;
    }
}
