package com.sajan.main;

import com.sajan.service.EmployeeServiceImpl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args )
    {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        File file = new File("Employee.xlsx");
        try {
            employeeService.readFromExcel(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
