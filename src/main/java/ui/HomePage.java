package ui;
import javax.swing.*;
import javax.swing.border.Border;
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
        topLabel.setBackground(new Color(23, 30, 23));
        topLabel.setForeground(new Color(139, 174, 102));

        homeFrame.add(topLabel, BorderLayout.NORTH);

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());
        homePanel.setSize(1920, 940);
        homePanel.setBackground(new Color(10, 130, 130));
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setPreferredSize(new Dimension(250, 940));

        Dimension btnSize = new Dimension(240, 70);


        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setBackground(new Color(139, 174, 102));
        addEmployeeButton.setForeground((Color.white));
        addEmployeeButton.setBorder(BorderFactory.createLineBorder(new Color(139,174,102)));
        addEmployeeButton.setFont(new Font("Poppins", Font.BOLD, 20));
        addEmployeeButton.setPreferredSize(btnSize);
        addEmployeeButton.setMaximumSize(btnSize);
        addEmployeeButton.setMinimumSize(btnSize);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeePage();
            }
        });

        JButton updateEmployeeButton = new JButton("Update Employee");
        updateEmployeeButton.setBackground(new Color(139, 174, 102));
        updateEmployeeButton.setForeground((Color.white));
        updateEmployeeButton.setBorder(BorderFactory.createLineBorder(new Color(139,174,102)));
        updateEmployeeButton.setFont(new Font("Poppins", Font.BOLD, 20));
        updateEmployeeButton.setPreferredSize(btnSize);
        updateEmployeeButton.setMaximumSize(btnSize);
        updateEmployeeButton.setMinimumSize(btnSize);
        updateEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateEmployeePage();
            }
        });

        JButton paySalaryButton = new JButton("Pay Salary");
        paySalaryButton.setBackground(new Color(139, 174, 102));
        paySalaryButton.setForeground((Color.white));
        paySalaryButton.setBorder(BorderFactory.createLineBorder(new Color(139,174,102)));
        paySalaryButton.setFont(new Font("Poppins", Font.BOLD, 20));
        paySalaryButton.setPreferredSize(btnSize);
        paySalaryButton.setMaximumSize(btnSize);
        paySalaryButton.setMinimumSize(btnSize);
        paySalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PaySalary();
            }
        });

        JButton viewSalaryReportButton = new JButton("View Salary Report");
        viewSalaryReportButton.setBackground(new Color(139, 174, 102));
        viewSalaryReportButton.setForeground((Color.white));
        viewSalaryReportButton.setBorder(BorderFactory.createLineBorder(new Color(139,174,102)));
        viewSalaryReportButton.setFont(new Font("Poppins", Font.BOLD, 20));
        viewSalaryReportButton.setPreferredSize(btnSize);
        viewSalaryReportButton.setMaximumSize(btnSize);
        viewSalaryReportButton.setMinimumSize(btnSize);
        viewSalaryReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewSalaryReport();
            }
        });

        JButton salaryReportByCategoryButton = new JButton("Salary By Category");
//        salaryReportByCategoryButton.set
        salaryReportByCategoryButton.setBackground(new Color(139, 174, 102));
        salaryReportByCategoryButton.setForeground((Color.white));
        salaryReportByCategoryButton.setBorder(BorderFactory.createLineBorder(new Color(139,174,102)));

        salaryReportByCategoryButton.setFont(new Font("Poppins", Font.BOLD, 20));
        salaryReportByCategoryButton.setPreferredSize(btnSize);
        salaryReportByCategoryButton.setMaximumSize(btnSize);
        salaryReportByCategoryButton.setMinimumSize(btnSize);
        salaryReportByCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SalaryReportByCategoryPage();
            }
        });

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(139, 174, 102));
        refreshButton.setForeground(Color.white);
        refreshButton.setBorder(BorderFactory.createLineBorder(new Color(139,174,102)));
        refreshButton.setFont(new Font("Poppins", Font.BOLD, 20));
        refreshButton.setPreferredSize(btnSize);
        refreshButton.setMaximumSize(btnSize);
        refreshButton.setMinimumSize(btnSize);

        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(addEmployeeButton);
        sidePanel.add(Box.createRigidArea(new Dimension(10, 20)));
        sidePanel.add(updateEmployeeButton);
        sidePanel.add(Box.createRigidArea(new Dimension(10, 20)));
        sidePanel.add(paySalaryButton);
        sidePanel.add(Box.createRigidArea(new Dimension(10, 20)));
        sidePanel.add(viewSalaryReportButton);
        sidePanel.add(Box.createRigidArea(new Dimension(10, 20)));
        sidePanel.add(salaryReportByCategoryButton);
        sidePanel.add(Box.createRigidArea(new Dimension(10, 20)));
        sidePanel.add(refreshButton);

        homePanel.add(sidePanel, BorderLayout.WEST);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(10, 130, 160));
        mainPanel.setSize(1670, 940);
        mainPanel.setBackground(new Color(139, 174, 102));
        // Teachers section
        JLabel teachersLabel = new JLabel("Teachers", SwingConstants.CENTER);
        teachersLabel.setPreferredSize(new Dimension(1670, 30));
        teachersLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
        teachersLabel.setForeground(Color.WHITE);
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
        officersLabel.setForeground(Color.WHITE);
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
        staffsLabel.setForeground(Color.WHITE);
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


