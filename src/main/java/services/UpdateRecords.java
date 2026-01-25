package services;

import entities.*;

public class UpdateRecords {

	public static void updateEmployee(Employee employee) {
		String sql = "UPDATE employees SET " +
				"name='" + employee.getName() + "', " +
				"age=" + employee.getAge() + ", " +
				"address='" + employee.getAddress() + "', " +
				"email='" + employee.getEmail() + "', " +
				"phone='" + employee.getPhone() + "', " +
				"designation='" + employee.getDesignation() + "', " +
				"basicSalary=" + employee.getSalary().getBasicSalary() + ", " +
				"rentAllowance=" + employee.getSalary().getRentAllowance() + ", " +
				"transportationAllowance=" + employee.getSalary().getTransportationAllowance() + ", " +
				"medicalAllowance=" + employee.getSalary().getMedicalAllowance() + ", " +
				"mobileAllowance=" + employee.getSalary().getMobileAllowance() + ", " +
				"healthInsurance=" + employee.getSalary().getHealthInsurance() + ", " +
				"providentFund=" + employee.getSalary().getProvidentFund() + ", " +
				"incomeTax=" + employee.getSalary().getIncomeTax() + ", " +
				"lunchService=" + employee.getSalary().getLunchService() +
				" WHERE employeeId=" + employee.getEmployeeId() + ";";
		new DatabaseService().executeUpdate(sql);
	}

	public static void updateOfficer(Officer officer) {
		updateEmployee(officer);
		String sql = "UPDATE officers SET " +
				"office='" + officer.getOffice() + "', " +
				"employmentType='" + officer.getEmploymentType() + "' " +
				"WHERE employeeId=" + officer.getEmployeeId() + ";";
		new DatabaseService().executeUpdate(sql);
	}

	public static void updateStaff(Staff staff) {
		updateEmployee(staff);
		String supervisorIdStr = (staff.getSupervisorId() == 0) ? "NULL" : String.valueOf(staff.getSupervisorId());
		String sql = "UPDATE staffs SET " +
				"supervisorId=" + supervisorIdStr + ", " +
				"faculty='" + staff.getWorkShift() + "' " + // Assuming workShift is used as faculty
				"WHERE employeeId=" + staff.getEmployeeId() + ";";
		new DatabaseService().executeUpdate(sql);
	}

	public static void updateTeacher(Teacher teacher) {
		updateEmployee(teacher);
		String sql = "UPDATE teachers SET " +
				"department='" + teacher.getDepartment() + "', " +
				"faculty='" + teacher.getFaculty() + "' " +
				"WHERE employeeId=" + teacher.getEmployeeId() + ";";
		new DatabaseService().executeUpdate(sql);
	}

	public static void updatePayrollRecord(PayrollRecord record) {
		String sql = "UPDATE payrollRecords SET " +
				"month=" + record.getMonth() + ", " +
				"year=" + record.getYear() + ", " +
				"disbursedOn='" + record.getDisbursedOn() + "', " +
				"basicSalary=" + record.getSalary().getBasicSalary() + ", " +
				"rentAllowance=" + record.getSalary().getRentAllowance() + ", " +
				"transportationAllowance=" + record.getSalary().getTransportationAllowance() + ", " +
				"medicalAllowance=" + record.getSalary().getMedicalAllowance() + ", " +
				"mobileAllowance=" + record.getSalary().getMobileAllowance() + ", " +
				"healthInsurance=" + record.getSalary().getHealthInsurance() + ", " +
				"providentFund=" + record.getSalary().getProvidentFund() + ", " +
				"incomeTax=" + record.getSalary().getIncomeTax() + ", " +
				"lunchService=" + record.getSalary().getLunchService() +
				" WHERE employeeId=" + record.getEmployeeId() + ";";
		new DatabaseService().executeUpdate(sql);
	}

}
