package Entities;

public class Payroll {
    private int employeeId;
    private int month;
    private int year;
    private String disbursedOn;
    private Salary salary;

    public Payroll(int employeeId, int month, int year, String disbursedOn,  Salary salary) {
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
        this.disbursedOn = disbursedOn;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getDisbursedOn() {
        return disbursedOn;
    }
    public void setDisbursedOn(String disbursedOn) {
        this.disbursedOn = disbursedOn;
    }
    public Salary getSalary() {
        return salary;
    }
    public void setSalary(Salary salary) {
        this.salary = salary;
    }
}

