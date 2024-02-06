package com.sajan.dao;

import com.sajan.model.Employee;
import com.sajan.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class EmployeeDao {
    private static final SessionFactory sessionFactory;

    // Initialize the SessionFactory when the class is loaded
    static {
        try {
            // Load configuration and mappings
            Configuration configuration = new Configuration().configure();

            // Build the SessionFactory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Provide a method to get the current Session
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    // Provide a method to get a transaction
    public static Transaction beginTransaction(Session session) {
        return session.beginTransaction();
    }



    // Close the SessionFactory
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
    public void saveEmployees(Employee employee) throws SQLException {
//        SessionFactory factory =null;
//        factory=new Configuration().configure().buildSessionFactory();
//
//        Session session=factory.openSession();
//
//        Transaction transaction=session.beginTransaction();
            Session session = HibernateUtil.getSession();
            Transaction transaction = HibernateUtil.beginTransaction(session);

            Employee saveEmployee = new Employee();
            saveEmployee.setEmpName(employee.getEmpName());
            saveEmployee.setAddress(employee.getAddress());
            saveEmployee.setSalary(employee.getSalary());

            session.save(saveEmployee);

            transaction.commit();
            closeSessionFactory();

    }
    public Employee getEmployeeById(Integer empId){

        Session session = getSession();

        return session.get(Employee.class, empId);

    }

    public List<Employee> getAllEmployees(){
//        List<Employee> listOfEmployees = new ArrayList<>();

        Query<Employee> query = null;
        try {
            Session session = getSession();
            String hql = "From Employee";
            query = session.createQuery(hql, Employee.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return query.list() ;
    }

    public void updateNameById(String newName, Integer empId){

        Session session = getSession();
        Transaction transaction = beginTransaction(session);
        Employee employee = session.get(Employee.class, empId);
        if(employee!= null){
            employee.setEmpName(newName);
            transaction.commit();
            System.out.println("Employee name updated successfully.");
        }
        closeSessionFactory();

    }
    public void updateAddressById(String newAddress, Integer empId){
        Session session = getSession();
        Transaction transaction = beginTransaction(session);
        Employee employee = session.get(Employee.class, empId);
        if(employee!= null){
            employee.setAddress(newAddress);
            transaction.commit();
            System.out.println("Employee address updated successfully.");
        }
        closeSessionFactory();

    }
    public void updateSalaryById(Integer newSalary, Integer empId){
        Session session = getSession();
        Transaction transaction = beginTransaction(session);
        Employee employee = session.get(Employee.class, empId);
        if(employee!= null){
            employee.setSalary(newSalary);
            transaction.commit();
            System.out.println("Employee salary updated successfully.");
        }

        closeSessionFactory();

    }


}
