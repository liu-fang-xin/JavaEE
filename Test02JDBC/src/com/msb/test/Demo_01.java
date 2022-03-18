package com.msb.test;

import com.msb.dao.EmpDao;
import com.msb.dao.impl.EmpDaoImpl;
import com.msb.poji.Emp;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo_01 {

    @Test
    public void testAddEmp(){
        Emp emp = new Emp(null,"JHON","MANAGER",7839,new Date(),3111.9,1000.78,30);
        EmpDao empDao = new EmpDaoImpl();
        int row = empDao.addEmp(emp);
        System.out.println("row = " + row);
    }
    @Test
    public void testDelete(){
        EmpDao empDao = new EmpDaoImpl();
        int rows = empDao.deleteByEmpno(7935);
        System.out.println("rows = " + rows);
    }
    @Test
    public void testFormatDate() throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/DD");
        String d = "1996/9/3";
        date =  sdf.parse(d);
        System.out.println("date = " + date);
        String r = d.substring(2,5);
        System.out.println("r = " + r);
    }
}
