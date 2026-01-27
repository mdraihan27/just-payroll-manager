package services;

import entities.Employee;
import entities.PayrollRecord;
import entities.Salary;
import entities.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetPayrollData {

    final static String URL = "jdbc:postgresql://localhost:5432/just_payroll_manager";
    final static String USER = "raihan";
    final static String PASSWORD = "raihan";


    public static PayrollRecord getPayrollById(int employeeId, int month, int year) throws SQLException {
        String query = "SELECT * FROM payrollRecords WHERE employeeId = " + employeeId + " AND month = " + month + " AND year = " + year + ";";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rsPayroll = stmt.executeQuery(query);
        if (rsPayroll == null || !rsPayroll.next()) {
            return null;
        }
        Salary salary = new Salary(
                rsPayroll.getInt("basicSalary"),
                rsPayroll.getInt("rentAllowance"),
                rsPayroll.getInt("transportationAllowance"),
                rsPayroll.getInt("medicalAllowance"),
                rsPayroll.getInt("mobileAllowance"),
                rsPayroll.getInt("healthInsurance"),
                rsPayroll.getInt("providentFund"),
                rsPayroll.getInt("incomeTax"),
                rsPayroll.getInt("lunchService")
        );
        PayrollRecord payrollRecord = new PayrollRecord(
                rsPayroll.getInt("employeeId"),
                rsPayroll.getInt("month"),
                rsPayroll.getInt("year"),
                rsPayroll.getString("disbursedOn"),
                salary);
        return payrollRecord;
    }

    public static int getPayrollByDept(String department) throws SQLException {

        String dep = department.replace("'", "''");
        String query =
                "SELECT pr.* FROM payrollRecords pr WHERE pr.employeeId IN (\n" +
                "  SELECT t.employeeId FROM teachers t WHERE t.department = '" + dep + "'\n" +
                ") OR pr.employeeId IN (\n" +
                "  SELECT s.employeeId FROM staffs s\n" +
                "  WHERE s.supervisorId IN (SELECT t2.employeeId FROM teachers t2 WHERE t2.department='" + dep + "')\n" +
                ");";

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rsPayroll = stmt.executeQuery(query);

        if (rsPayroll == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }

        int total = getPayrollSumFromResultSet(rsPayroll);
        try { rsPayroll.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;

    }

    public static int getPayrollByDept(String department, int month, int year) throws SQLException {
        String dep = department.replace("'", "''");
        String query =
                "SELECT pr.* FROM payrollRecords pr WHERE pr.month = " + month + " AND pr.year = " + year + " AND (\n" +
                "  pr.employeeId IN (SELECT t.employeeId FROM teachers t WHERE t.department = '" + dep + "')\n" +
                "  OR pr.employeeId IN (SELECT s.employeeId FROM staffs s WHERE s.supervisorId IN (SELECT t2.employeeId FROM teachers t2 WHERE t2.department='" + dep + "'))\n" +
                ")";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static int getPayrollByFaculty(String faculty) throws SQLException {

        String fac = faculty.replace("'", "''");
        String query =
                "SELECT pr.* FROM payrollRecords pr WHERE pr.employeeId IN (\n" +
                "  SELECT t.employeeId FROM teachers t WHERE t.faculty = '" + fac + "'\n" +
                ") OR pr.employeeId IN (\n" +
                "  SELECT s.employeeId FROM staffs s\n" +
                "  WHERE s.supervisorId IN (SELECT t2.employeeId FROM teachers t2 WHERE t2.faculty='" + fac + "')\n" +
                ");";

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rsPayroll = stmt.executeQuery(query);

        if (rsPayroll == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }

        int total = getPayrollSumFromResultSet(rsPayroll);
        try { rsPayroll.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;

    }

    public static int getPayrollByFaculty(String faculty, int month, int year) throws SQLException {
        String fac = faculty.replace("'", "''");
        String query =
                "SELECT pr.* FROM payrollRecords pr WHERE pr.month = " + month + " AND pr.year = " + year + " AND (\n" +
                "  pr.employeeId IN (SELECT t.employeeId FROM teachers t WHERE t.faculty = '" + fac + "')\n" +
                "  OR pr.employeeId IN (SELECT s.employeeId FROM staffs s WHERE s.supervisorId IN (SELECT t2.employeeId FROM teachers t2 WHERE t2.faculty='" + fac + "'))\n" +
                ")";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static int getPayrollByDesignation(String designation) throws SQLException {

        String des = designation.replace("'", "''");
        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.employeeId IN (\n" +
                "  SELECT e.employeeId FROM employees e WHERE e.designation = '" + des + "'\n" +
                ");";

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rsPayroll = stmt.executeQuery(query);

        if (rsPayroll == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }

        int total = getPayrollSumFromResultSet(rsPayroll);
        try { rsPayroll.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;

    }
    public static int getPayrollByDesignation(String designation, int month, int year) throws SQLException {
        String des = designation.replace("'", "''");
        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.month = " + month + " AND pr.year = " + year +
                " AND pr.employeeId IN (SELECT e.employeeId FROM employees e WHERE e.designation = '" + des + "')";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }
    public static int getPayrollByOffice(String office) throws SQLException {

        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.employeeId IN (\n" +
                "  SELECT o.employeeId FROM officers o WHERE o.office = '" + office + "'\n" +
                ");";

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rsPayroll = stmt.executeQuery(query);
        if (rsPayroll == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }
        int total = getPayrollSumFromResultSet(rsPayroll);
        try { rsPayroll.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static int getPayrollByOffice(String office, int month, int year) throws SQLException {
        String off = office.replace("'", "''");
        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.month = " + month + " AND pr.year = " + year +
                " AND pr.employeeId IN (SELECT o.employeeId FROM officers o WHERE o.office = '" + off + "')";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static int getTotalDisbursedForTeachers() throws SQLException {
        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.employeeId IN (SELECT employeeId FROM teachers);";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static int getTotalDisbursedForOfficers() throws SQLException {
        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.employeeId IN (SELECT employeeId FROM officers);";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static int getTotalDisbursedForStaffs() throws SQLException {
        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.employeeId IN (SELECT employeeId FROM staffs);";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static int getTotalDisbursedForTeachers(int month, int year) throws SQLException {
        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.month = " + month + " AND pr.year = " + year +
                " AND pr.employeeId IN (SELECT employeeId FROM teachers)";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) { try { stmt.close(); } catch (Exception ignore) {} try { conn.close(); } catch (Exception ignore) {} return 0; }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static int getTotalDisbursedForOfficers(int month, int year) throws SQLException {
        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.month = " + month + " AND pr.year = " + year +
                " AND pr.employeeId IN (SELECT employeeId FROM officers)";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) { try { stmt.close(); } catch (Exception ignore) {} try { conn.close(); } catch (Exception ignore) {} return 0; }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static int getTotalDisbursedForStaffs(int month, int year) throws SQLException {
        String query = "SELECT pr.* FROM payrollRecords pr WHERE pr.month = " + month + " AND pr.year = " + year +
                " AND pr.employeeId IN (SELECT employeeId FROM staffs)";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) { try { stmt.close(); } catch (Exception ignore) {} try { conn.close(); } catch (Exception ignore) {} return 0; }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }


    public static int getTotalDisbursedPayroll() throws SQLException {

        String query = "SELECT *\n" +
                "FROM payrollRecords\n" ;

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rsPayroll = stmt.executeQuery(query);

        if (rsPayroll == null) {
            try { stmt.close(); } catch (Exception ignore) {}
            try { conn.close(); } catch (Exception ignore) {}
            return 0;
        }

        int total = getPayrollSumFromResultSet(rsPayroll);
        try { rsPayroll.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;

    }

    public static int getTotalDisbursedPayroll(int month, int year) throws SQLException {
        String query = "SELECT * FROM payrollRecords WHERE month = " + month + " AND year = " + year + ";";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs == null) { try { stmt.close(); } catch (Exception ignore) {} try { conn.close(); } catch (Exception ignore) {} return 0; }
        int total = getPayrollSumFromResultSet(rs);
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return total;
    }

    public static boolean hasAnyPayrollForMonthYear(int month, int year) throws SQLException {
        String query = "SELECT 1 FROM payrollRecords WHERE month = " + month + " AND year = " + year + " LIMIT 1;";
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        boolean exists = rs != null && rs.next();
        try { rs.close(); } catch (Exception ignore) {}
        try { stmt.close(); } catch (Exception ignore) {}
        try { conn.close(); } catch (Exception ignore) {}
        return exists;
    }

    public static int getPayrollSumFromResultSet(ResultSet rsPayroll) throws SQLException {
        int sum = 0;
        while(rsPayroll.next()) {
            sum+= rsPayroll.getInt("basicSalary")+
                    rsPayroll.getInt("rentAllowance")+
                    rsPayroll.getInt("transportationAllowance")+
                    rsPayroll.getInt("medicalAllowance")+
                    rsPayroll.getInt("mobileAllowance")-
                    rsPayroll.getInt("healthInsurance")-
                    rsPayroll.getInt("providentFund")-
                    rsPayroll.getInt("incomeTax")-
                    rsPayroll.getInt("lunchService");
        }
        return sum;
    }

}
