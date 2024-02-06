package com.sajan.service;

import com.sajan.dao.EmployeeDao;
import com.sajan.model.Employee;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeServiceImpl extends EmployeeDao {

    private static EmployeeDao employeeDao;
    private static final Scanner input = new Scanner(System.in);

    public void readFromExcel(File file) throws EncryptedDocumentException, IOException, SQLException, ClassNotFoundException {

        if(file.getName().contains("Employee")){
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet("Employee");

            employeeDao = new EmployeeDao();
            for(Row row : sheet) {
                if (row.getRowNum() != 0) {
                    if (row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                        // Break the loop if the first cell of the current row is empty
                        break;
                    }
                    Employee employee = new Employee(
                            (int) row.getCell(0).getNumericCellValue(),
                            String.valueOf(row.getCell(1)),
                            String.valueOf(row.getCell(2)),
                            (int) row.getCell(3).getNumericCellValue());
                    employeeDao.saveEmployees(employee);
                }
            }

        }
    }

    public void updateEmployee() throws SQLException, ClassNotFoundException {

        employeeDao = new EmployeeDao();
        List<Employee> allEmployees = employeeDao.getAllEmployees();
        System.out.println("empId\tname\taddress\tsalary");
        for(Employee emp:  allEmployees){

            System.out.println(emp.getEmpId()+
                    "\t\t"+emp.getEmpName()+
                    "\t"+emp.getAddress()+
                    "\t\t"+emp.getSalary());
        }

        System.out.print("Enter the employee id that you want to update: \n");
        Integer updateEmpId = Integer.parseInt(input.nextLine()); // get the emp id from the user

        Employee employeeById = employeeDao.getEmployeeById(updateEmpId);
        System.out.println("You want to update employee "+ employeeById.getEmpId());


        System.out.println("1. update name\n2. update address\n3. update salary"); // choice for the user
        Integer updateInput = Integer.parseInt(input.nextLine());

        String updatedName, updatedAddress;
        Integer updatedSalary;
        if(updateInput == 1 ){
            System.out.println("Enter new name: ");
            updatedName = input.nextLine();
            employeeDao.updateNameById(updatedName, updateEmpId);

        } else if(updateInput == 2){
            System.out.println("Enter new address: ");
            updatedAddress = input.nextLine();
            employeeDao.updateAddressById(updatedAddress, updateEmpId);
        } else if (updateInput == 3){
            System.out.println("Enter new salary: ");
            updatedSalary = Integer.parseInt(input.nextLine());
            employeeDao.updateSalaryById(updatedSalary, updateEmpId);
        }

    }

    public void writeToExcel() {
    }
}
