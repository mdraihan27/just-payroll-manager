package services;

import entities.*;
import java.sql.*;
import java.util.*;

public class GetEmployeeData {

	public static List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();

        String query = "SELECT * FROM teachers;";
        ResultSet rsTeacher = DatabaseService.executeQuery(query);

        if(rsTeacher == null){
            return null;
        }
        while (rsTeacher.next()) {
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
        ResultSet rsOfficer = DatabaseService.executeQuery(query);

        if(rsOfficer==null){
            return null;
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
        ResultSet rsStaff = DatabaseService.executeQuery(query);

        if (rsStaff == null) {
            return null;
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
        ResultSet rs = DatabaseService.executeQuery(query);

        if (rs == null) {
            return null;
        }

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
