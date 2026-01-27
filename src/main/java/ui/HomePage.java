package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import entities.*;
import services.*;


public class HomePage {

    public HomePage() throws SQLException {
        JFrame homeFrame = new JFrame("Home Page");
        homeFrame.setSize(1920, 1000);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLayout(new BorderLayout());

        JLabel topLabel = new JLabel("JUST Payroll Manager", SwingConstants.CENTER);
        topLabel.setPreferredSize(new Dimension(1920, 60));
        topLabel.setFont(new Font("Poppins", Font.BOLD, 40));
        topLabel.setOpaque(true);
        topLabel.setBackground(new Color(70, 130, 180));
        topLabel.setForeground(Color.WHITE);

        homeFrame.add(topLabel, BorderLayout.NORTH);

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());
        homePanel.setSize(1920, 940);
        homePanel.setBackground(new Color(10, 130, 130));
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(10, 100, 130));
        sidePanel.setPreferredSize(new Dimension(250, 940));

        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setBackground(new Color(70, 130, 180));
        addEmployeeButton.setForeground(Color.WHITE);
        addEmployeeButton.setFont(new Font("Poppins", Font.BOLD, 20));
        addEmployeeButton.setSize(new Dimension(200, 100));

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeePage();
            }
        });

        JButton updateEmployeeButton = new JButton("Update Employee");
        updateEmployeeButton.setBackground(new Color(70, 130, 180));
        updateEmployeeButton.setForeground(Color.WHITE);
        updateEmployeeButton.setFont(new Font("Poppins", Font.BOLD, 20));
        updateEmployeeButton.setSize(new Dimension(200, 100));
        updateEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateEmployeePage();
            }
        });

        JButton paySalaryButton = new JButton("Pay Salary");
        paySalaryButton.setBackground(new Color(70, 130, 180));
        paySalaryButton.setForeground(Color.WHITE);
        paySalaryButton.setFont(new Font("Poppins", Font.BOLD, 20));
        paySalaryButton.setSize(new Dimension(200, 100));
        paySalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PaySalary();
            }
        });

        JButton viewSalaryReportButton = new JButton("View Salary Report");
        viewSalaryReportButton.setBackground(new Color(70, 130, 180));
        viewSalaryReportButton.setForeground(Color.WHITE);
        viewSalaryReportButton.setFont(new Font("Poppins", Font.BOLD, 20));
        viewSalaryReportButton.setSize(new Dimension(200, 100));
        viewSalaryReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewSalaryReport();
            }
        });

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(70, 130, 180));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Poppins", Font.BOLD, 20));
        refreshButton.setSize(new Dimension(200, 100));

        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(addEmployeeButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(updateEmployeeButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(paySalaryButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(viewSalaryReportButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(refreshButton);

        homePanel.add(sidePanel, BorderLayout.WEST);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(10, 130, 160));
        mainPanel.setSize(1670, 940);

        // Teachers section
        JLabel teachersLabel = new JLabel("Teachers", SwingConstants.CENTER);
        teachersLabel.setPreferredSize(new Dimension(1670, 30));
        teachersLabel.setFont(new Font("Poppins", Font.BOLD, 15));
        teachersLabel.setForeground(Color.BLACK);
        teachersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(teachersLabel);

        String[] columnNames = {"ID", "Name", "Age", "Address", "Email", "Phone", "Designation", "Department", "Faculty",
            "Basic Salary", "Rent Allowance", "Transportation Allowance", "Medical Allowance", "Mobile Allowance",
            "Health Insurance", "Provident Fund", "Income Tax", "Lunch Service"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable teachersTable = new JTable(model);
        teachersTable.setRowHeight(30);
        // Set preferred column widths for name, email, phone, designation
        int[] teacherColIdx = {1, 4, 5, 6};
        for (int idx : teacherColIdx) {
            teachersTable.getColumnModel().getColumn(idx).setPreferredWidth(130);
        }
        JScrollPane scrollPane = new JScrollPane(teachersTable);
        mainPanel.add(scrollPane);

        // Helper to load teachers data
        Runnable loadTeachers = () -> {
            try {
                List<Teacher> teachers = GetEmployeeData.getAllTeachers();
                model.setRowCount(0);
                for (Teacher teacher : teachers) {
                    Salary salary = teacher.getSalary();
                    model.addRow(new Object[] {
                        teacher.getEmployeeId(),
                        teacher.getName(),
                        teacher.getAge(),
                        teacher.getAddress(),
                        teacher.getEmail(),
                        teacher.getPhone(),
                        teacher.getDesignation(),
                        teacher.getDepartment(),
                        teacher.getFaculty(),
                        (salary != null) ? salary.getBasicSalary() : "",
                        (salary != null) ? salary.getRentAllowance() : "",
                        (salary != null) ? salary.getTransportationAllowance() : "",
                        (salary != null) ? salary.getMedicalAllowance() : "",
                        (salary != null) ? salary.getMobileAllowance() : "",
                        (salary != null) ? salary.getHealthInsurance() : "",
                        (salary != null) ? salary.getProvidentFund() : "",
                        (salary != null) ? salary.getIncomeTax() : "",
                        (salary != null) ? salary.getLunchService() : ""
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };
        loadTeachers.run();

        // Officers section
        JLabel officersLabel = new JLabel("Officers", SwingConstants.CENTER);
        officersLabel.setPreferredSize(new Dimension(1670, 30));
        officersLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
        officersLabel.setForeground(Color.BLACK);
        officersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(officersLabel);

        String[] columnNames2 = {"ID", "Name", "Age", "Address", "Email", "Phone", "Designation", "Office", "Employment Type",
                "Basic Salary", "Rent Allowance", "Transportation Allowance", "Medical Allowance", "Mobile Allowance",
                "Health Insurance", "Provident Fund", "Income Tax", "Lunch Service"};
        DefaultTableModel officersModel = new DefaultTableModel(columnNames2, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable officersTable = new JTable(officersModel);
        officersTable.setRowHeight(30);
        // Set preferred column widths for name, email, phone, designation
        int[] officerColIdx = {1, 4, 5, 6};
        for (int idx : officerColIdx) {
            officersTable.getColumnModel().getColumn(idx).setPreferredWidth(130);
        }
        JScrollPane officersScrollPane = new JScrollPane(officersTable);
        mainPanel.add(officersScrollPane);

        Runnable loadOfficers = () -> {
            try {
                List<Officer> officers = GetEmployeeData.getAllOfficers();
                officersModel.setRowCount(0);
                for (Officer officer : officers) {
                    Salary salary = officer.getSalary();
                    officersModel.addRow(new Object[] {
                        officer.getEmployeeId(),
                        officer.getName(),
                        officer.getAge(),
                        officer.getAddress(),
                        officer.getEmail(),
                        officer.getPhone(),
                        officer.getDesignation(),
                        officer.getOffice(),
                        officer.getEmploymentType(),
                        (salary != null) ? salary.getBasicSalary() : "",
                        (salary != null) ? salary.getRentAllowance() : "",
                        (salary != null) ? salary.getTransportationAllowance() : "",
                        (salary != null) ? salary.getMedicalAllowance() : "",
                        (salary != null) ? salary.getMobileAllowance() : "",
                        (salary != null) ? salary.getHealthInsurance() : "",
                        (salary != null) ? salary.getProvidentFund() : "",
                        (salary != null) ? salary.getIncomeTax() : "",
                        (salary != null) ? salary.getLunchService() : ""
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };
        loadOfficers.run();

        // Staffs section
        JLabel staffsLabel = new JLabel("Staffs", SwingConstants.CENTER);
        staffsLabel.setPreferredSize(new Dimension(1670, 30));
        staffsLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
        staffsLabel.setForeground(Color.BLACK);
        staffsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(staffsLabel);

        String[] columnNames3 = {"ID", "Name", "Age", "Address", "Email", "Phone", "Designation", "Supervisor ID", "Work Shift",
                "Basic Salary", "Rent Allowance", "Transportation Allowance", "Medical Allowance", "Mobile Allowance",
                "Health Insurance", "Provident Fund", "Income Tax", "Lunch Service"};
        DefaultTableModel staffsModel = new DefaultTableModel(columnNames3, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable staffsTable = new JTable(staffsModel);
        staffsTable.setRowHeight(30);
        // Set preferred column widths for name, email, phone, designation
        int[] staffColIdx = {1, 4, 5, 6};
        for (int idx : staffColIdx) {
            staffsTable.getColumnModel().getColumn(idx).setPreferredWidth(130);
        }
        JScrollPane staffsScrollPane = new JScrollPane(staffsTable);
        mainPanel.add(staffsScrollPane);

        Runnable loadStaffs = () -> {
            try {
                List<Staff> staffs = GetEmployeeData.getAllStaffs();
                staffsModel.setRowCount(0);
                for (Staff staff : staffs) {
                    Salary salary = staff.getSalary();
                    staffsModel.addRow(new Object[] {
                        staff.getEmployeeId(),
                        staff.getName(),
                        staff.getAge(),
                        staff.getAddress(),
                        staff.getEmail(),
                        staff.getPhone(),
                        staff.getDesignation(),
                        staff.getSupervisorId(),
                        staff.getWorkShift(),
                        (salary != null) ? salary.getBasicSalary() : "",
                        (salary != null) ? salary.getRentAllowance() : "",
                        (salary != null) ? salary.getTransportationAllowance() : "",
                        (salary != null) ? salary.getMedicalAllowance() : "",
                        (salary != null) ? salary.getMobileAllowance() : "",
                        (salary != null) ? salary.getHealthInsurance() : "",
                        (salary != null) ? salary.getProvidentFund() : "",
                        (salary != null) ? salary.getIncomeTax() : "",
                        (salary != null) ? salary.getLunchService() : ""
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };
        loadStaffs.run();
        // Refresh button action
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTeachers.run();
                loadOfficers.run();
                loadStaffs.run();
            }
        });

        homePanel.add(mainPanel);
        homeFrame.add(homePanel);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
    }

}
