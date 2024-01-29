package com.sajan.service;

import com.sajan.dao.EmployeeDao;
import com.sajan.model.Employee;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class EmployeeServiceImpl extends EmployeeDao {

    EmployeeDao employeeDao;

    public void readFromExcel(File file) throws EncryptedDocumentException, FileNotFoundException, IOException, SQLException, ClassNotFoundException {

        if(file.getName().contains("Employee")){
            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet("Employee");
            String rowFields = "";

            employeeDao = new EmployeeDao();
            for(Row row : sheet){
                if(row.getRowNum() !=0 ){
                        Employee employee=new Employee((int)row.getCell(0).getNumericCellValue(),
                                String.valueOf(row.getCell(1)),
                                String.valueOf(row.getCell(3)),
                                (int)row.getCell(2).getNumericCellValue());
                        employeeDao.saveEmployees(employee);
                }
            }

        }
    }
}
