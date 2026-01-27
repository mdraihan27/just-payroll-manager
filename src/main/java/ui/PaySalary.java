package ui;

import entities.*;
import services.AddNewRecords;
import services.GetEmployeeData;
import services.UpdateRecords;
import services.GetPayrollData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class PaySalary {
	public PaySalary() {
		JFrame frame = new JFrame("Pay Salary");
		frame.setSize(400, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		JTextField monthField = new JTextField(20);
		JTextField yearField = new JTextField(20);

		gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
		panel.add(new JLabel("Month (1-12):"), gbc);
		gbc.gridx = 1; gbc.weightx = 0.7;
		panel.add(monthField, gbc);
		gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
		panel.add(new JLabel("Year (e.g. 2026):"), gbc);
		gbc.gridx = 1; gbc.weightx = 0.7;
		panel.add(yearField, gbc);

		JButton payBtn = new JButton("Pay Salary");
		gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
		panel.add(payBtn, gbc);
		gbc.gridwidth = 1;

		frame.add(panel, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		payBtn.addActionListener(e -> {
			try {
				int month = Integer.parseInt(monthField.getText().trim());
				int year = Integer.parseInt(yearField.getText().trim());
				if (month < 1 || month > 12) throw new Exception("Month must be 1-12");
				if (year < 2000 || year > 3000) throw new Exception("Year out of range");

				int prevMonth = (month == 1) ? 12 : (month - 1);
				int prevYear = (month == 1) ? (year - 1) : year;

				// Check if current month is already paid
				boolean alreadyPaid = GetPayrollData.hasAnyPayrollForMonthYear(month, year);
				if (alreadyPaid) {
				    JOptionPane.showMessageDialog(frame,
					    "Salary for this month (" + month + "/" + year + ") has already been paid.",
					    "Payment Blocked",
					    JOptionPane.WARNING_MESSAGE);
				    return;
				}

				boolean previousPaid = GetPayrollData.hasAnyPayrollForMonthYear(prevMonth, prevYear);
				if (!previousPaid) {
				    JOptionPane.showMessageDialog(frame,
					    "Previous month's salary not found. Please ensure " + prevMonth + "/" + prevYear +
						    " is paid first.",
					    "Payment Blocked",
					    JOptionPane.WARNING_MESSAGE);
				    return;
				}

				int count = paySalaryForAll(month, year);
				JOptionPane.showMessageDialog(frame, "Salary paid for " + count + " employees.");
				frame.dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private int paySalaryForAll(int month, int year) {
		int count = 0;
		try {
			List<Employee> employees = GetEmployeeData.getAllEmployees();
			String today = LocalDate.now().toString();
			for (Employee emp : employees) {
				PayrollRecord record = new PayrollRecord(
					emp.getEmployeeId(),
					month,
					year,
					today,
					emp.getSalary()
				);
				AddNewRecords.addPayrollRecord(record);
				count++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}


}
