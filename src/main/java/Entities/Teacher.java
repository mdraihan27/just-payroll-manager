package Entities;

public class Teacher extends Employee {

    private String department;
    private String faculty;

    public Teacher(int employeeId, String name, int age, String address,
                   String email, String phone,String designation, Salary salary,  String department) {
        super(employeeId, name, age, address, email, phone, designation, salary);

        this.department = department;

        if (department.equals("CSE") || department.equals("IPE") || department.equals("PME") ||
            department.equals("ChE") || department.equals("EEE") || department.equals("BME") ||
            department.equals("TE")) {
            this.faculty = "FET";
        } else if (department.equals("MB") || department.equals("FMB") || department.equals("GEB") ||
               department.equals("PHAR") || department.equals("BMB")) {
            this.faculty = "FBST";
        } else if (department.equals("EST") || department.equals("NFT") || department.equals("FE") ||
               department.equals("CDM")) {
            this.faculty = "FAST";
        } else if (department.equals("PESS") || department.equals("PR") || department.equals("NHS")) {
            this.faculty = "FHS";
        } else if (department.equals("ENG")) {
            this.faculty = "FASS";
        } else if (department.equals("PHY") || department.equals("CHE") || department.equals("MAT") ||
               department.equals("ASDS")) {
            this.faculty = "FS";
        } else if (department.equals("AIS") || department.equals("MGT") || department.equals("FB") ||
               department.equals("MKT")) {
            this.faculty = "FBS";
        } else {
            this.faculty = "UNKNOWN";
        }
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
