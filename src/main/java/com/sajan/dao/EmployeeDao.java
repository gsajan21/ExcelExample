package com.sajan.dao;

import com.sajan.model.Employee;
import com.sajan.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDao {
    public void saveEmployees(Employee employee)throws ClassNotFoundException, SQLException {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = con.prepareStatement("insert into Employee (empId, emdName, address, salary) values (?,?,?,?)");

        ps.setInt(1, employee.getEmpId());
        ps.setString(2, employee.getEmpName());
        ps.setString(3, employee.getAddress());
        ps.setInt(4, employee.getSalary());

        ps.execute();
        System.out.println("Record has been inserted");
        ps.close();
        con.close();
    }
}
