package com.msb.dao.impl;

import com.msb.dao.EmpDao;
import com.msb.poji.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDaoImpl implements EmpDao {

        private static String driver ="com.mysql.cj.jdbc.Driver";
        private static String url="jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        private static String user="root";
        private static String password="root";
        @Override
        public int addEmp(Emp emp) {
            // 向 Emp表中增加一条数据
            Connection connection = null;
            PreparedStatement preparedStatement=null;
            int rows=0;
            try{
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user,password);
                String sql="insert into emp values(DEFAULT ,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
                //设置参数
                preparedStatement.setObject(1,emp.getEname());
                preparedStatement.setObject(2,emp.getJob() );
                preparedStatement.setObject(3,emp.getMgr());
                preparedStatement.setObject(4,emp.getHiredate());
                preparedStatement.setObject(5,emp.getSal());
                preparedStatement.setObject(6,emp.getComm());
                preparedStatement.setObject(7,emp.getDeptno());
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
        @Override
        public int deleteByEmpno(int empno) {
            // 向 Emp表中增加一条数据
            Connection connection = null;
            PreparedStatement preparedStatement=null;
            int rows=0;
            try{
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user,password);
                String sql="delete from emp where empno =?";
                preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
                //设置参数
                preparedStatement.setObject(1,empno);
                //执行CURD
                rows =preparedStatement.executeUpdate();
                System.out.println("row = " + rows);// 这里不需要再传入SQL语句
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

    @Override
    public List<Emp> findAll() {
        // 查询名字中包含字母A的员工信息
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<Emp> list =null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user,password);
            String sql="select * from emp";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            list=new ArrayList<Emp>() ;
            while(resultSet.next()){
                int empno = resultSet.getInt("empno");
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int mgr = resultSet.getInt("mgr");
                Date hiredate = resultSet.getDate("hiredate");
                double sal= resultSet.getDouble("sal");
                double comm= resultSet.getDouble("comm");
                int deptno= resultSet.getInt("deptno");
                Emp emp =new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
                list.add(emp);
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
    public int updateEmp(Emp emp) {
        // 向 Emp表中增加一条数据
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        int rows=0;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user,password);
            String sql="update emp set ename =? ,job=?, mgr =?,hiredate =?,sal=?,comm=?,deptno=? where empno =?";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            preparedStatement.setObject(1,emp.getEname());
            preparedStatement.setObject(2,emp.getJob() );
            preparedStatement.setObject(3,emp.getMgr());
            preparedStatement.setObject(4,emp.getHiredate());
            preparedStatement.setObject(5,emp.getSal());
            preparedStatement.setObject(6,emp.getComm());
            preparedStatement.setObject(7,emp.getDeptno());
            preparedStatement.setObject(8,emp.getEmpno());
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
