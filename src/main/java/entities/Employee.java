package entities;

public class Employee {

    private final int employeeId;
    private String name;
    private int age;
    private String address;
    private String email;
    private String phone;
    private String designation;
    private Salary salary;
    public Employee(int employeeId, String name, int age, String address, String email, String phone, String designation, Salary salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.designation = designation;
        this.salary = salary;

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Salary getSalary() {
        return salary;
    }
    public void setSalary(Salary salary) {
        this.salary = salary;
    }

}
