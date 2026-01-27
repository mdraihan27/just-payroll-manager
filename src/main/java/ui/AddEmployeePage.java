
package ui;

import entities.*;
import services.AddNewRecords;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AddEmployeePage {
	public AddEmployeePage() {
		JFrame frame = new JFrame("Add Employee");
		frame.setSize(700, 850);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		// Employee fields
		JTextField idField = new JTextField(20);
		JTextField nameField = new JTextField(20);
		JTextField ageField = new JTextField(20);
		JTextField addressField = new JTextField(20);
		JTextField emailField = new JTextField(20);
		JTextField phoneField = new JTextField(20);
		JTextField designationField = new JTextField(20);

		// Salary fields
		JTextField basicSalaryField = new JTextField(20);
		JTextField rentAllowanceField = new JTextField(20);
		JTextField transportationAllowanceField = new JTextField(20);
		JTextField medicalAllowanceField = new JTextField(20);
		JTextField mobileAllowanceField = new JTextField(20);
		JTextField healthInsuranceField = new JTextField(20);
		JTextField providentFundField = new JTextField(20);
		JTextField incomeTaxField = new JTextField(20);
		JTextField lunchServiceField = new JTextField(20);

		// Category radio buttons
		JRadioButton teacherRadio = new JRadioButton("Teacher", true);
		JRadioButton officerRadio = new JRadioButton("Officer");
		JRadioButton staffRadio = new JRadioButton("Staff");
		ButtonGroup group = new ButtonGroup();
		group.add(teacherRadio);
		group.add(officerRadio);
		group.add(staffRadio);

		JPanel radioPanel = new JPanel();
		radioPanel.add(new JLabel("Category: "));
		radioPanel.add(teacherRadio);
		radioPanel.add(officerRadio);
		radioPanel.add(staffRadio);

		// Teacher-specific
		JTextField departmentField = new JTextField(20);

		// Officer-specific
		JTextField officeField = new JTextField(20);
		JTextField employmentTypeField = new JTextField(20);

		// Staff-specific
		JTextField supervisorIdField = new JTextField(20);
		JTextField workShiftField = new JTextField(20);

		// Helper to enable/disable fields
		Runnable updateFields = () -> {
			boolean isTeacher = teacherRadio.isSelected();
			boolean isOfficer = officerRadio.isSelected();
			boolean isStaff = staffRadio.isSelected();
			departmentField.setEnabled(isTeacher);
			officeField.setEnabled(isOfficer);
			employmentTypeField.setEnabled(isOfficer);
			supervisorIdField.setEnabled(isStaff);
			workShiftField.setEnabled(isStaff);
		};
		ActionListener radioListener = e -> updateFields.run();
		teacherRadio.addActionListener(radioListener);
		officerRadio.addActionListener(radioListener);
		staffRadio.addActionListener(radioListener);
		updateFields.run();

		int row = 0;
		String[] labels = {
			"Employee ID:", "Name:", "Age:", "Address:", "Email:", "Phone:", "Designation:",
			"Basic Salary:", "Rent Allowance:", "Transportation Allowance:", "Medical Allowance:", "Mobile Allowance:",
			"Health Insurance:", "Provident Fund:", "Income Tax:", "Lunch Service:",
			"Department (Teacher):", "Office (Officer):", "Employment Type (Officer):", "Supervisor ID (Staff):", "Work Shift (Staff):"
		};
		JComponent[] fields = {
			idField, nameField, ageField, addressField, emailField, phoneField, designationField,
			basicSalaryField, rentAllowanceField, transportationAllowanceField, medicalAllowanceField, mobileAllowanceField,
			healthInsuranceField, providentFundField, incomeTaxField, lunchServiceField,
			departmentField, officeField, employmentTypeField, supervisorIdField, workShiftField
		};
		for (int i = 0; i < labels.length; i++) {
			gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.3;
			panel.add(new JLabel(labels[i]), gbc);
			gbc.gridx = 1; gbc.weightx = 0.7;
			panel.add(fields[i], gbc);
			row++;
		}
		// Category radio panel
		gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
		panel.add(radioPanel, gbc);
		gbc.gridwidth = 1;
		row++;
		// Submit button
		JButton submitBtn = new JButton("Submit");
		gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
		panel.add(submitBtn, gbc);
		gbc.gridwidth = 1;

		submitBtn.addActionListener(e -> {
			try {
				int employeeId = Integer.parseInt(idField.getText().trim());
				String name = nameField.getText().trim();
				int age = Integer.parseInt(ageField.getText().trim());
				String address = addressField.getText().trim();
				String email = emailField.getText().trim();
				String phone = phoneField.getText().trim();
				String designation = designationField.getText().trim();
				int basicSalary = Integer.parseInt(basicSalaryField.getText().trim());
				int rentAllowance = Integer.parseInt(rentAllowanceField.getText().trim());
				int transportationAllowance = Integer.parseInt(transportationAllowanceField.getText().trim());
				int medicalAllowance = Integer.parseInt(medicalAllowanceField.getText().trim());
				int mobileAllowance = Integer.parseInt(mobileAllowanceField.getText().trim());
				int healthInsurance = Integer.parseInt(healthInsuranceField.getText().trim());
				int providentFund = Integer.parseInt(providentFundField.getText().trim());
				int incomeTax = Integer.parseInt(incomeTaxField.getText().trim());
				int lunchService = Integer.parseInt(lunchServiceField.getText().trim());
				Salary salary = new Salary(basicSalary, rentAllowance, transportationAllowance, medicalAllowance, mobileAllowance, healthInsurance, providentFund, incomeTax, lunchService);

				if (teacherRadio.isSelected()) {
					String department = departmentField.getText().trim();
					Teacher teacher = new Teacher(employeeId, name, age, address, email, phone, designation, salary, department);
					AddNewRecords.addTeacher(teacher);
					JOptionPane.showMessageDialog(frame, "Teacher added successfully!");
				} else if (officerRadio.isSelected()) {
					String office = officeField.getText().trim();
					String employmentType = employmentTypeField.getText().trim();
					Officer officer = new Officer(employeeId, name, age, address, email, phone, designation, salary, office, employmentType);
					AddNewRecords.addOfficer(officer);
					JOptionPane.showMessageDialog(frame, "Officer added successfully!");
				} else if (staffRadio.isSelected()) {
					int supervisorId = 0;
					try { supervisorId = Integer.parseInt(supervisorIdField.getText().trim()); } catch (Exception ex) {}
					String workShift = workShiftField.getText().trim();
					Staff staff = new Staff(employeeId, name, age, address, email, phone, designation, salary, supervisorId, workShift);
					AddNewRecords.addStaff(staff);
					JOptionPane.showMessageDialog(frame, "Staff added successfully!");
				}
				frame.dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		JScrollPane scrollPane = new JScrollPane(panel);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
