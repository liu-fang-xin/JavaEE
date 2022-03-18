package com.msb.dao;

import com.msb.poji.Dept;

import java.util.List;

public interface DeptDao {
    /**
     * 查询全部门的方法
     * @return Dept对象封装的List集合
     */
    List<Dept> findAll();
    int addDept(Dept dept);
}
