package com.msb.dao;

import com.msb.poji.Emp;

import java.util.List;

public interface EmpDao {

    /**
     * 向数据库Emp表中增加一条数据的方法
     * @param emp 要增加的数据封装成的Emp类的对象
     * @return 增加成功返回大于0 的整数,增加失败返回0
     */
    int addEmp(Emp emp);
    /**
     * 根据员工编号删除员工信息的方法
     * @param empno 要删除的员工编号
     * @return 删除成功返回大于0的整数,失败返回0
     */
    int deleteByEmpno(int empno);

    /**
     * 查看数据库表格中所有的员工信息
     * @return 所有员工信息封装的一个List<Emp>集合
     */
    List<Emp> findAll();
    /**
     * 根据员工编号修改员工其他所有字段的方法
     * @param emp 员工编号和其他7个字段封装的一个Emp类对象
     * @return 修改成功返回大于0的整数,失败返回0
     */
    int updateEmp(Emp emp);
}
