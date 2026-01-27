package services;

import entities.*;
import java.sql.*;
import java.util.*;

public class GetEmployeeData {

    public static List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees;";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Salary salary = new Salary(
                rs.getInt("basicSalary"),
                rs.getInt("rentAllowance"),
                rs.getInt("transportationAllowance"),
                rs.getInt("medicalAllowance"),
                rs.getInt("mobileAllowance"),
                rs.getInt("healthInsurance"),
                rs.getInt("providentFund"),
                rs.getInt("incomeTax"),
                rs.getInt("lunchService")
            );
            Employee employee = new Employee(
                rs.getInt("employeeId"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("designation"),
                salary
            );
            employees.add(employee);
        }
        return employees;
    }

    public static Teacher getTeacherById(int employeeId) throws SQLException {
        String query = "SELECT * FROM teachers WHERE employeeId = " + employeeId + ";";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            Employee employee = getEmployeeById(employeeId);
            return new Teacher(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getAge(),
                employee.getAddress(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDesignation(),
                employee.getSalary(),
                rs.getString("department")
            );
        }
        return null;
    }

    public static Officer getOfficerById(int employeeId) throws SQLException {
        String query = "SELECT * FROM officers WHERE employeeId = " + employeeId + ";";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            Employee employee = getEmployeeById(employeeId);
            return new Officer(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getAge(),
                employee.getAddress(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDesignation(),
                employee.getSalary(),
                rs.getString("office"),
                rs.getString("employmentType")
            );
        }
        return null;
    }

    public static Staff getStaffById(int employeeId) throws SQLException {
        String query = "SELECT * FROM staffs WHERE employeeId = " + employeeId + ";";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            Employee employee = getEmployeeById(employeeId);
            return new Staff(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getAge(),
                employee.getAddress(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDesignation(),
                employee.getSalary(),
                rs.getInt("supervisorId"),
                rs.getString("workShift")
            );
        }
        return null;
    }

    final static String URL = "jdbc:postgresql://localhost:5432/just_payroll_manager";
    final static String USER = "raihan";
    final static String PASSWORD = "raihan";

	public static List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();

        String query = "SELECT * FROM teachers;";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rsTeacher = stmt.executeQuery(query);

//        if(rsTeacher == null || rsTeacher.isClosed()){
//            return List.of();
//        }
        while (rsTeacher.next()) {
            System.out.println("loop entered");
            Employee employee = getEmployeeById(rsTeacher.getInt("employeeId"));

            Teacher teacher = new Teacher(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getAge(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getPhone(),
                    employee.getDesignation(),
                    employee.getSalary(),
                    rsTeacher.getString("department")
            );
            teachers.add(teacher);
        }

        return teachers;
    }



    public static List<Officer> getAllOfficers() throws SQLException {
        List<Officer> officers = new ArrayList<>();
        String query = "SELECT * FROM officers;";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rsOfficer = stmt.executeQuery(query);

        if(rsOfficer== null || rsOfficer.isClosed()){
            return List.of();
        }
        while (rsOfficer.next()) {
            Employee employee = getEmployeeById(rsOfficer.getInt("employeeId"));

            Officer officer = new Officer(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getAge(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getPhone(),
                    employee.getDesignation(),
                    employee.getSalary(),
                    rsOfficer.getString("office"),
                    rsOfficer.getString("employmentType")
            );
            officers.add(officer);
        }

        return officers;
    }

    public static List<Staff> getAllStaffs() throws SQLException {
        List<Staff> staffs = new ArrayList<>();
        String query = "SELECT * FROM staffs;";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rsStaff = stmt.executeQuery(query);

        if (rsStaff == null || rsStaff.isClosed()){
            return List.of();
        }
        while (rsStaff.next()) {
            Employee employee = getEmployeeById(rsStaff.getInt("employeeId"));

            Staff staff = new Staff(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getAge(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getPhone(),
                    employee.getDesignation(),
                    employee.getSalary(),
                    rsStaff.getInt("supervisorId"),
                    rsStaff.getString("workShift")
            );
            staffs.add(staff);
        }

        return staffs;
    }

    public static Employee getEmployeeById(int employeeId) throws SQLException {
        String query = "SELECT * FROM employees\n WHERE employeeId = "+employeeId+";";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs == null) {
            return null;
        }
        rs.next();

            Salary salary = new Salary(
                    rs.getInt("basicSalary"),
                    rs.getInt("rentAllowance"),
                    rs.getInt("transportationAllowance"),
                    rs.getInt("medicalAllowance"),
                    rs.getInt("mobileAllowance"),
                    rs.getInt("healthInsurance"),
                    rs.getInt("providentFund"),
                    rs.getInt("incomeTax"),
                    rs.getInt("lunchService")
            );

        Employee employee = new Employee(rs.getInt("employeeId"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("designation"),
                salary);


        return employee;
    }



}
