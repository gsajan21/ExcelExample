package com.sajan.model;

public class Employee {

    private int empId;
    private String empName;
    private String address;
    private Integer salary;

    public Employee(int empId, String empName, String address, Integer salary) {
        this.empId = empId;
        this.empName = empName;
        this.address = address;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}