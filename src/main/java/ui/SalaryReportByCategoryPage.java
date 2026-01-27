package ui;

import services.GetPayrollData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaryReportByCategoryPage {

	public SalaryReportByCategoryPage() {
		JFrame frame = new JFrame("Salary Report By Category");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;

		String[] categories = new String[] {
				"All employees",
				"Teachers",
				"Officers",
				"Staffs",
				"Salary by Dept",
				"Salary by Faculty",
				"Salary by Office",
				"Salary by Designation"
		};

		JLabel categoryLabel = new JLabel("Category:");
		JComboBox<String> categoryCombo = new JComboBox<>(categories);

		JLabel valueLabel = new JLabel("Value:");
		JTextField valueField = new JTextField(20);

		JLabel monthLabel = new JLabel("Month (1-12):");
		JTextField monthField = new JTextField(10);
		JLabel yearLabel = new JLabel("Year:");
		JTextField yearField = new JTextField(10);

		JButton calcBtn = new JButton("Calculate Total");

		gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(categoryLabel, gbc);
		gbc.gridx = 1; gbc.gridy = 0; inputPanel.add(categoryCombo, gbc);
		gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(valueLabel, gbc);
		gbc.gridx = 1; gbc.gridy = 1; inputPanel.add(valueField, gbc);
		gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(monthLabel, gbc);
		gbc.gridx = 1; gbc.gridy = 2; inputPanel.add(monthField, gbc);
		gbc.gridx = 0; gbc.gridy = 3; inputPanel.add(yearLabel, gbc);
		gbc.gridx = 1; gbc.gridy = 3; inputPanel.add(yearField, gbc);
		gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; inputPanel.add(calcBtn, gbc);
		gbc.gridwidth = 1;

		JTextArea resultArea = new JTextArea(8, 50);
		resultArea.setEditable(false);
		JScrollPane resultScroll = new JScrollPane(resultArea);

		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(resultScroll, BorderLayout.CENTER);

		// Toggle value field visibility based on category
		ActionListener toggleInput = e -> {
			String sel = (String) categoryCombo.getSelectedItem();
			boolean needsValue = "Salary by Dept".equals(sel)
					|| "Salary by Faculty".equals(sel)
					|| "Salary by Office".equals(sel)
					|| "Salary by Designation".equals(sel);
			valueLabel.setVisible(needsValue);
			valueField.setVisible(needsValue);
			valueField.setEnabled(needsValue);
			if (!needsValue) valueField.setText("");
		};
		categoryCombo.addActionListener(toggleInput);
		toggleInput.actionPerformed(null);

		calcBtn.addActionListener(e -> {
			String sel = (String) categoryCombo.getSelectedItem();
			String inputVal = valueField.getText().trim();
			try {
				int m = Integer.parseInt(monthField.getText().trim());
				int y = Integer.parseInt(yearField.getText().trim());
				if (m < 1 || m > 12) throw new IllegalArgumentException("Month must be 1-12");

				int total = 0;
				if ("All employees".equals(sel)) {
					total = GetPayrollData.getTotalDisbursedPayroll(m, y);
				} else if ("Teachers".equals(sel)) {
					total = GetPayrollData.getTotalDisbursedForTeachers(m, y);
				} else if ("Officers".equals(sel)) {
					total = GetPayrollData.getTotalDisbursedForOfficers(m, y);
				} else if ("Staffs".equals(sel)) {
					total = GetPayrollData.getTotalDisbursedForStaffs(m, y);
				} else if ("Salary by Dept".equals(sel)) {
					if (inputVal.isEmpty()) throw new IllegalArgumentException("Please enter a department.");
					total = GetPayrollData.getPayrollByDept(inputVal, m, y);
				} else if ("Salary by Faculty".equals(sel)) {
					if (inputVal.isEmpty()) throw new IllegalArgumentException("Please enter a faculty.");
					total = GetPayrollData.getPayrollByFaculty(inputVal, m, y);
				} else if ("Salary by Office".equals(sel)) {
					if (inputVal.isEmpty()) throw new IllegalArgumentException("Please enter an office.");
					total = GetPayrollData.getPayrollByOffice(inputVal, m, y);
				} else if ("Salary by Designation".equals(sel)) {
					if (inputVal.isEmpty()) throw new IllegalArgumentException("Please enter a designation.");
					total = GetPayrollData.getPayrollByDesignation(inputVal, m, y);
				}

				resultArea.setText("Total Disbursed Salary (" + m + "/" + y + "): " + total);
			} catch (Exception ex) {
				resultArea.setText("Error: " + ex.getMessage());
			}
		});

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
