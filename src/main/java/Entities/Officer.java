package Entities;

public class Officer extends Employee{
    private String office;
    private String employmentType;


    public Officer(int employeeId, String name, int age, String address,
                   String email, String phone, String designation, Salary salary, String office, String employmentType) {

        super(employeeId, name, age, address, email, phone, designation, salary);
        this.office = office;
        this.employmentType = employmentType;
    }

    public String getOffice() {
        return office;
    }
    public void setOffice(String office) {
        this.office = office;
    }

    public String getEmploymentType() {
        return employmentType;
    }
    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
}
