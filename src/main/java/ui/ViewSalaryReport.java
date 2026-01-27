package ui;

import entities.PayrollRecord;
import entities.Salary;
import services.GetPayrollData;
import services.GetEmployeeData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewSalaryReport {
	public ViewSalaryReport() {
		JFrame frame = new JFrame("View Salary Report");
		frame.setSize(500, 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		JTextField idField = new JTextField(20);
		JTextField monthField = new JTextField(20);
		JTextField yearField = new JTextField(20);

		gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
		inputPanel.add(new JLabel("Employee ID:"), gbc);
		gbc.gridx = 1; gbc.weightx = 0.7;
		inputPanel.add(idField, gbc);
		gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
		inputPanel.add(new JLabel("Month (1-12):"), gbc);
		gbc.gridx = 1; gbc.weightx = 0.7;
		inputPanel.add(monthField, gbc);
		gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
		inputPanel.add(new JLabel("Year (e.g. 2026):"), gbc);
		gbc.gridx = 1; gbc.weightx = 0.7;
		inputPanel.add(yearField, gbc);

		JButton searchBtn = new JButton("Search");
		gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
		inputPanel.add(searchBtn, gbc);
		gbc.gridwidth = 1;

		JTextArea resultArea = new JTextArea(10, 40);
		resultArea.setEditable(false);
		JScrollPane resultScroll = new JScrollPane(resultArea);

		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(resultScroll, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		searchBtn.addActionListener(e -> {
			try {
				int employeeId = Integer.parseInt(idField.getText().trim());
				int month = Integer.parseInt(monthField.getText().trim());
				int year = Integer.parseInt(yearField.getText().trim());
				PayrollRecord record = GetPayrollData.getPayrollById(employeeId, month, year);
				if (record == null) {
					resultArea.setText("No payroll record found for the given employee, month, and year.");
				} else {
					Salary s = record.getSalary();
					String employeeName = "";
					try {
						employeeName = GetEmployeeData.getEmployeeById(employeeId).getName();
					} catch (Exception ex2) {
						employeeName = "(Name not found)";
					}

					// Calculate total salary: sum all allowances, subtract all deductions
					int totalAllowances = s.getBasicSalary()
						+ s.getRentAllowance()
						+ s.getTransportationAllowance()
						+ s.getMedicalAllowance()
						+ s.getMobileAllowance();
					int totalDeductions = s.getIncomeTax()
						+ s.getHealthInsurance()
						+ s.getProvidentFund()
						+ s.getLunchService();
					int totalSalary = totalAllowances - totalDeductions;

					StringBuilder sb = new StringBuilder();
					sb.append("==============================\n");
					sb.append("        SALARY REPORT\n");
					sb.append("==============================\n");
					sb.append(String.format("Employee ID   : %d\n", record.getEmployeeId()));
					sb.append(String.format("Employee Name : %s\n", employeeName));
					sb.append(String.format("Month         : %d\n", record.getMonth()));
					sb.append(String.format("Year          : %d\n", record.getYear()));
					sb.append(String.format("Disbursed On  : %s\n", record.getDisbursedOn()));
					sb.append("------------------------------\n");
					sb.append("Allowances:\n");
					sb.append(String.format("  Basic Salary           : %d\n", s.getBasicSalary()));
					sb.append(String.format("  Rent Allowance         : %d\n", s.getRentAllowance()));
					sb.append(String.format("  Transportation Allow.  : %d\n", s.getTransportationAllowance()));
					sb.append(String.format("  Medical Allowance      : %d\n", s.getMedicalAllowance()));
					sb.append(String.format("  Mobile Allowance       : %d\n", s.getMobileAllowance()));
					sb.append("------------------------------\n");
					sb.append("Deductions:\n");
					sb.append(String.format("  Income Tax             : %d\n", s.getIncomeTax()));
					sb.append(String.format("  Health Insurance       : %d\n", s.getHealthInsurance()));
					sb.append(String.format("  Provident Fund         : %d\n", s.getProvidentFund()));
					sb.append(String.format("  Lunch Service          : %d\n", s.getLunchService()));
					sb.append("------------------------------\n");
					sb.append(String.format("TOTAL SALARY             : %d\n", totalSalary));
					sb.append("==============================\n");
					resultArea.setText(sb.toString());
				}
			} catch (Exception ex) {
				resultArea.setText("Error: " + ex.getMessage());
			}
		});
	}
}
