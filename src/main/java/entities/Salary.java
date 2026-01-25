package entities;

public class Salary {
    private int basicSalary;

    private int rentAllowance;
    private int transportationAllowance;
    private int medicalAllowance;
    private int mobileAllowance;

    private int healthInsurance;
    private int providentFund;
    private int incomeTax;
    private int lunchService;

    public Salary(int basicSalary, int rentAllowance, int transportationAllowance, int medicalAllowance, int mobileAllowance,
                  int healthInsurance, int providentFund, int incomeTax, int lunchService) {
        this.basicSalary = basicSalary;
        this.rentAllowance = rentAllowance;
        this.transportationAllowance = transportationAllowance;
        this.medicalAllowance = medicalAllowance;
        this.mobileAllowance = mobileAllowance;
        this.healthInsurance = healthInsurance;
        this.providentFund = providentFund;
        this.incomeTax = incomeTax;
        this.lunchService = lunchService;
    }

    public int getBasicSalary() {
        return basicSalary;
    }
    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }
    public int getRentAllowance() {
        return rentAllowance;
    }
    public void setRentAllowance(int rentAllowance) {
        this.rentAllowance = rentAllowance;
    }
    public int getTransportationAllowance() {
        return transportationAllowance;
    }
    public void setTransportationAllowance(int transportationAllowance) {
        this.transportationAllowance = transportationAllowance;

    }
    public int getMedicalAllowance() {
        return medicalAllowance;
    }
    public void setMedicalAllowance(int medicalAllowance) {
        this.medicalAllowance = medicalAllowance;
    }
    public int getMobileAllowance() {
        return mobileAllowance;
    }
    public void setMobileAllowance(int mobileAllowance) {
        this.mobileAllowance = mobileAllowance;
    }
    public int getHealthInsurance() {
        return healthInsurance;
    }
    public void setHealthInsurance(int healthInsurance) {
        this.healthInsurance = healthInsurance;
    }
    public int getProvidentFund() {
        return providentFund;
    }
    public void setProvidentFund(int providentFund) {
        this.providentFund = providentFund;
    }
    public int getIncomeTax() {
        return incomeTax;
    }
    public void setIncomeTax(int incomeTax) {
        this.incomeTax = incomeTax;
    }
    public int getLunchService() {
        return lunchService;
    }
    public void setLunchService(int lunchService) {
        this.lunchService = lunchService;
    }



}
