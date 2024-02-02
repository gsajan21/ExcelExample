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

        try {
            File file = new File("Employee.xlsx");
            employeeService.readFromExcel(file);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
