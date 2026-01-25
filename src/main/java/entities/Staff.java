package entities;

public class Staff extends Employee {

    private int supervisorId;
    private String workShift;

    public Staff(int employeeId, String name, int age, String address, String email, String phone, String designation, Salary salary, int supervisorId, String workShift) {
        super(employeeId, name, age, address, email, phone, designation, salary);

        this.supervisorId = supervisorId;
        this.workShift = workShift;
    }

    public int getSupervisorId() {
        return supervisorId;
    }
    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getWorkShift() {
        return workShift;
    }
    public void setWorkShift(String workShift) {
        this.workShift = workShift;
    }
}
