package com.sajan.service;

import com.sajan.dao.EmployeeDao;
import com.sajan.model.Employee;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class EmployeeServiceImpl extends EmployeeDao {

    EmployeeDao employeeDao;

    public void readFromExcel(File file) throws EncryptedDocumentException, IOException, SQLException, ClassNotFoundException {

        if(file.getName().contains("Employee")){
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet("Employee");

            employeeDao = new EmployeeDao();
            for(Row row : sheet){
                if(row.getRowNum() != 0 ){
                    if (row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                        // Break the loop if the first cell of the current row is empty
                        break;
                    }
                        Employee employee=new Employee(
                                (int)row.getCell(0).getNumericCellValue(),
                                String.valueOf(row.getCell(1)),
                                String.valueOf(row.getCell(2)),
                                (int) row.getCell(3).getNumericCellValue());
                        employeeDao.saveEmployees(employee);
                }
            }

        }
    }
}
