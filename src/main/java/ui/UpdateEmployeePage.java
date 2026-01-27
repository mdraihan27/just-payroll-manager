package ui;

import entities.*;
import services.GetEmployeeData;
import services.UpdateRecords;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateEmployeePage {
	public UpdateEmployeePage() {
		JFrame frame = new JFrame("Update Employee");
		frame.setSize(700, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		JTextField idField = new JTextField(20);
		String[] types = {"Teacher", "Officer", "Staff"};
		JComboBox<String> typeBox = new JComboBox<>(types);

		gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
		inputPanel.add(new JLabel("Employee ID:"), gbc);
		gbc.gridx = 1; gbc.weightx = 0.7;
		inputPanel.add(idField, gbc);
		gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
		inputPanel.add(new JLabel("Employee Type:"), gbc);
		gbc.gridx = 1; gbc.weightx = 0.7;
		inputPanel.add(typeBox, gbc);

		JButton nextBtn = new JButton("Next");
		gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
		inputPanel.add(nextBtn, gbc);
		gbc.gridwidth = 1;

		frame.add(inputPanel, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		nextBtn.addActionListener(e -> {
			try {
				int employeeId = Integer.parseInt(idField.getText().trim());
				String type = (String) typeBox.getSelectedItem();
				frame.dispose();
				showEditPage(employeeId, type);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void showEditPage(int employeeId, String type) {
		JFrame frame = new JFrame("Edit Employee");
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
		JTextField idField = new JTextField(20); idField.setEditable(false);
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

		// Category radio buttons (disabled, just for info)
		JRadioButton teacherRadio = new JRadioButton("Teacher");
		JRadioButton officerRadio = new JRadioButton("Officer");
		JRadioButton staffRadio = new JRadioButton("Staff");
		ButtonGroup group = new ButtonGroup();
		group.add(teacherRadio);
		group.add(officerRadio);
		group.add(staffRadio);
		teacherRadio.setEnabled(false);
		officerRadio.setEnabled(false);
		staffRadio.setEnabled(false);

		JPanel radioPanel = new JPanel();
		radioPanel.add(new JLabel("Category: "));
		radioPanel.add(teacherRadio);
		radioPanel.add(officerRadio);
		radioPanel.add(staffRadio);

		// Teacher-specific
		JTextField departmentField = new JTextField(20);
		departmentField.setEnabled(false);
		JTextField facultyField = new JTextField(20);
		facultyField.setEnabled(false);

		// Officer-specific
		JTextField officeField = new JTextField(20);
		officeField.setEnabled(false);
		JTextField employmentTypeField = new JTextField(20);
		employmentTypeField.setEnabled(false);

		// Staff-specific
		JTextField supervisorIdField = new JTextField(20);
		supervisorIdField.setEnabled(false);
		JTextField workShiftField = new JTextField(20);
		workShiftField.setEnabled(false);

		// Load data from DB
		try {
			if (type.equals("Teacher")) {
				teacherRadio.setSelected(true);
				departmentField.setEnabled(true);
				facultyField.setEnabled(true);
				Teacher teacher = GetEmployeeData.getTeacherById(employeeId);
				if (teacher == null) throw new Exception("Teacher not found");
				idField.setText(String.valueOf(teacher.getEmployeeId()));
				nameField.setText(teacher.getName());
				ageField.setText(String.valueOf(teacher.getAge()));
				addressField.setText(teacher.getAddress());
				emailField.setText(teacher.getEmail());
				phoneField.setText(teacher.getPhone());
				designationField.setText(teacher.getDesignation());
				Salary salary = teacher.getSalary();
				if (salary != null) {
					basicSalaryField.setText(String.valueOf(salary.getBasicSalary()));
					rentAllowanceField.setText(String.valueOf(salary.getRentAllowance()));
					transportationAllowanceField.setText(String.valueOf(salary.getTransportationAllowance()));
					medicalAllowanceField.setText(String.valueOf(salary.getMedicalAllowance()));
					mobileAllowanceField.setText(String.valueOf(salary.getMobileAllowance()));
					healthInsuranceField.setText(String.valueOf(salary.getHealthInsurance()));
					providentFundField.setText(String.valueOf(salary.getProvidentFund()));
					incomeTaxField.setText(String.valueOf(salary.getIncomeTax()));
					lunchServiceField.setText(String.valueOf(salary.getLunchService()));
				}
				departmentField.setText(teacher.getDepartment());
				facultyField.setText(teacher.getFaculty());
			} else if (type.equals("Officer")) {
				officerRadio.setSelected(true);
				officeField.setEnabled(true);
				employmentTypeField.setEnabled(true);
				Officer officer = GetEmployeeData.getOfficerById(employeeId);
				if (officer == null) throw new Exception("Officer not found");
				idField.setText(String.valueOf(officer.getEmployeeId()));
				nameField.setText(officer.getName());
				ageField.setText(String.valueOf(officer.getAge()));
				addressField.setText(officer.getAddress());
				emailField.setText(officer.getEmail());
				phoneField.setText(officer.getPhone());
				designationField.setText(officer.getDesignation());
				Salary salary = officer.getSalary();
				if (salary != null) {
					basicSalaryField.setText(String.valueOf(salary.getBasicSalary()));
					rentAllowanceField.setText(String.valueOf(salary.getRentAllowance()));
					transportationAllowanceField.setText(String.valueOf(salary.getTransportationAllowance()));
					medicalAllowanceField.setText(String.valueOf(salary.getMedicalAllowance()));
					mobileAllowanceField.setText(String.valueOf(salary.getMobileAllowance()));
					healthInsuranceField.setText(String.valueOf(salary.getHealthInsurance()));
					providentFundField.setText(String.valueOf(salary.getProvidentFund()));
					incomeTaxField.setText(String.valueOf(salary.getIncomeTax()));
					lunchServiceField.setText(String.valueOf(salary.getLunchService()));
				}
				officeField.setText(officer.getOffice());
				employmentTypeField.setText(officer.getEmploymentType());
			} else if (type.equals("Staff")) {
				staffRadio.setSelected(true);
				supervisorIdField.setEnabled(true);
				workShiftField.setEnabled(true);
				Staff staff = GetEmployeeData.getStaffById(employeeId);
				if (staff == null) throw new Exception("Staff not found");
				idField.setText(String.valueOf(staff.getEmployeeId()));
				nameField.setText(staff.getName());
				ageField.setText(String.valueOf(staff.getAge()));
				addressField.setText(staff.getAddress());
				emailField.setText(staff.getEmail());
				phoneField.setText(staff.getPhone());
				designationField.setText(staff.getDesignation());
				Salary salary = staff.getSalary();
				if (salary != null) {
					basicSalaryField.setText(String.valueOf(salary.getBasicSalary()));
					rentAllowanceField.setText(String.valueOf(salary.getRentAllowance()));
					transportationAllowanceField.setText(String.valueOf(salary.getTransportationAllowance()));
					medicalAllowanceField.setText(String.valueOf(salary.getMedicalAllowance()));
					mobileAllowanceField.setText(String.valueOf(salary.getMobileAllowance()));
					healthInsuranceField.setText(String.valueOf(salary.getHealthInsurance()));
					providentFundField.setText(String.valueOf(salary.getProvidentFund()));
					incomeTaxField.setText(String.valueOf(salary.getIncomeTax()));
					lunchServiceField.setText(String.valueOf(salary.getLunchService()));
				}
				supervisorIdField.setText(String.valueOf(staff.getSupervisorId()));
				workShiftField.setText(staff.getWorkShift());
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
			frame.dispose();
			return;
		}

		int row = 0;
		String[] labels = {
			"Employee ID:", "Name:", "Age:", "Address:", "Email:", "Phone:", "Designation:",
			"Basic Salary:", "Rent Allowance:", "Transportation Allowance:", "Medical Allowance:", "Mobile Allowance:",
			"Health Insurance:", "Provident Fund:", "Income Tax:", "Lunch Service:",
			"Department (Teacher):", "Faculty (Teacher):", "Office (Officer):", "Employment Type (Officer):", "Supervisor ID (Staff):", "Work Shift (Staff):"
		};
		JComponent[] fields = {
			idField, nameField, ageField, addressField, emailField, phoneField, designationField,
			basicSalaryField, rentAllowanceField, transportationAllowanceField, medicalAllowanceField, mobileAllowanceField,
			healthInsuranceField, providentFundField, incomeTaxField, lunchServiceField,
			departmentField, facultyField, officeField, employmentTypeField, supervisorIdField, workShiftField
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
		// Update button
		JButton updateBtn = new JButton("Update");
		gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
		panel.add(updateBtn, gbc);
		gbc.gridwidth = 1;

		updateBtn.addActionListener(e -> {
			try {
				int empId = Integer.parseInt(idField.getText().trim());
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

				if (type.equals("Teacher")) {
					String department = departmentField.getText().trim();
					Teacher teacher = new Teacher(empId, name, age, address, email, phone, designation, salary, department);
					UpdateRecords.updateTeacher(teacher);
					JOptionPane.showMessageDialog(frame, "Teacher updated successfully!");
				} else if (type.equals("Officer")) {
					String office = officeField.getText().trim();
					String employmentType = employmentTypeField.getText().trim();
					Officer officer = new Officer(empId, name, age, address, email, phone, designation, salary, office, employmentType);
					UpdateRecords.updateOfficer(officer);
					JOptionPane.showMessageDialog(frame, "Officer updated successfully!");
				} else if (type.equals("Staff")) {
					int supervisorId = 0;
					try { supervisorId = Integer.parseInt(supervisorIdField.getText().trim()); } catch (Exception ex) {}
					String workShift = workShiftField.getText().trim();
					Staff staff = new Staff(empId, name, age, address, email, phone, designation, salary, supervisorId, workShift);
					UpdateRecords.updateStaff(staff);
					JOptionPane.showMessageDialog(frame, "Staff updated successfully!");
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
