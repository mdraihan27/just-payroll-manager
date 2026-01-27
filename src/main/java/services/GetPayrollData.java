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

        String query = "SELECT *\n" +
                "FROM payrollRecords\n" +
                "WHERE employeeId IN (\n" +
                "    SELECT employeeId\n" +
                "    FROM teachers\n" +
                "    WHERE department = "+department+"\n" +
                ");\n";

        ResultSet rsPayroll = DatabaseService.executeQuery(query);

        if (rsPayroll == null) {
            return 0;
        }

        return getPayrollSumFromResultSet(rsPayroll);

    }

    public static int getPayrollByFaculty(String faculty) throws SQLException {

        String query = "SELECT *\n" +
                "FROM payrollRecords\n" +
                "WHERE employeeId IN (\n" +
                "    SELECT employeeId\n" +
                "    FROM teachers\n" +
                "    WHERE faculty = "+faculty+"\n" +
                ");\n";

        ResultSet rsPayroll = DatabaseService.executeQuery(query);

        if (rsPayroll == null) {
            return 0;
        }

        return getPayrollSumFromResultSet(rsPayroll);

    }

    public static int getPayrollByDesignation(String designation) throws SQLException {

        String query = "SELECT *\n" +
                "FROM payrollRecords\n" +
                "WHERE employeeId IN (\n" +
                "    SELECT employeeId\n" +
                "    FROM teachers\n" +
                "    WHERE designation = "+designation+"\n" +
                ");\n";

        ResultSet rsPayroll = DatabaseService.executeQuery(query);

        if (rsPayroll == null) {
            return 0;
        }

        return getPayrollSumFromResultSet(rsPayroll);

    }

    public static int getTotalDisbursedPayroll() throws SQLException {

        String query = "SELECT *\n" +
                "FROM payrollRecords\n" ;

        ResultSet rsPayroll = DatabaseService.executeQuery(query);

        if (rsPayroll == null) {
            return 0;
        }

        return getPayrollSumFromResultSet(rsPayroll);

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
