package services;

import entities.*;

public class AddNewRecords {

    public static void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (employeeId, name, age, address, email, phone, designation, " +
                "basicSalary, rentAllowance, transportationAllowance, medicalAllowance, mobileAllowance, " +
                "healthInsurance, providentFund, incomeTax, lunchService) "
                +
                "VALUES (" + employee.getEmployeeId() + ", '" + employee.getName() + "', " + employee.getAge() + ", '"
                + employee.getAddress() + "', '" + employee.getEmail() + "', '" + employee.getPhone() + "', '"
                + employee.getDesignation() + "', " +
                employee.getSalary().getBasicSalary() + ", " + employee.getSalary().getRentAllowance() + ", "
                + employee.getSalary().getTransportationAllowance() + ", " + employee.getSalary().getMedicalAllowance()
                + ", " +
                employee.getSalary().getMobileAllowance() + ", " + employee.getSalary().getHealthInsurance() + ", "
                + employee.getSalary().getProvidentFund() + ", " + employee.getSalary().getIncomeTax() + ", "
                + employee.getSalary().getLunchService() + ");";
        new DatabaseService().executeUpdate(sql);
    }

    public static void addOfficer(Officer officer) {
        addEmployee(officer);
        String sql = "INSERT INTO officers (employeeId, office, employmentType) VALUES (" + officer.getEmployeeId()
                + ", '" + officer.getOffice() + "', '" + officer.getEmploymentType() + "');";
        new DatabaseService().executeUpdate(sql);
    }

    public static void addStaff(Staff staff) {
        addEmployee(staff);
        String supervisorIdStr = (staff.getSupervisorId() == 0) ? "NULL" : String.valueOf(staff.getSupervisorId());
        String sql = "INSERT INTO staffs (employeeId, supervisorId, faculty) VALUES (" + staff.getEmployeeId() + ", "
                + supervisorIdStr + ", '" + staff.getWorkShift() + "');";
        new DatabaseService().executeUpdate(sql);
    }

    public static void addTeacher(Teacher teacher) {
        addEmployee(teacher);
        String sql = "INSERT INTO teachers (employeeId, department, faculty) VALUES (" + teacher.getEmployeeId() + ", '"
                + teacher.getDepartment() + "', '" + teacher.getFaculty() + "');";
        new DatabaseService().executeUpdate(sql);
    }

    public static void addPayrollRecord(PayrollRecord record) {
        String sql = "INSERT INTO payrollRecords (employeeId, month, year, disbursedOn, basicSalary, " +
                "rentAllowance, transportationAllowance, medicalAllowance, mobileAllowance, healthInsurance, " +
                "providentFund, incomeTax, lunchService) "
                +
                "VALUES (" + record.getEmployeeId() + ", " + record.getMonth() + ", " + record.getYear() + ", '"
                + record.getDisbursedOn() + "', " +
                record.getSalary().getBasicSalary() + ", " + record.getSalary().getRentAllowance() + ", "
                + record.getSalary().getTransportationAllowance() + ", " + record.getSalary().getMedicalAllowance()
                + ", " +
                record.getSalary().getMobileAllowance() + ", " + record.getSalary().getHealthInsurance() + ", "
                + record.getSalary().getProvidentFund() + ", " + record.getSalary().getIncomeTax() + ", "
                + record.getSalary().getLunchService() + ");";
        new DatabaseService().executeUpdate(sql);
    }

}
