package services;

public class CreateTables {

    static final String createTablesQuery = ""
            + "CREATE TABLE employees ("
            + "    employeeId INT PRIMARY KEY,"
            + "    name VARCHAR(50) NOT NULL,"
            + "    age INT NOT NULL,"
            + "    address VARCHAR(100) NOT NULL,"
            + "    email VARCHAR(50) UNIQUE NOT NULL,"
            + "    phone VARCHAR(20),"
            + "    designation VARCHAR(100),"
            + "    basicSalary INT NOT NULL,"
            + "    rentAllowance INT NOT NULL,"
            + "    transportationAllowance INT NOT NULL,"
            + "    medicalAllowance INT NOT NULL,"
            + "    mobileAllowance INT NOT NULL,"
            + "    healthInsurance INT NOT NULL,"
            + "    providentFund INT NOT NULL,"
            + "    incomeTax INT NOT NULL,"
            + "    lunchService INT NOT NULL"
            + ");"

            + "CREATE TABLE teachers ("
            + "    employeeId INT PRIMARY KEY ,"
            + "    department VARCHAR(50) NOT NULL,"
            + "    faculty VARCHAR(20)"
            + ");"

            + "CREATE TABLE officers ("
            + "    employeeId INT PRIMARY KEY,"
            + "    office VARCHAR(50) NOT NULL,"
            + "    employmentType VARCHAR(20)"
            + ");"

            + "CREATE TABLE staffs ("
            + "    employeeId INT PRIMARY KEY,"
            + "    supervisorId INT ,"
            + "    workShift VARCHAR(20),"
            + "    CONSTRAINT fk_supervisor "
            + "        FOREIGN KEY (supervisorId) "
            + "        REFERENCES employees(employeeId) "
            + "        ON DELETE SET NULL "
            + "        ON UPDATE CASCADE"
            + ");"

            + "CREATE TABLE payrollRecords ("
            + "    employeeId INT PRIMARY KEY,"
            + "    month INT ,"
            + "    year INT ,"
            + "    disbursedOn VARCHAR(20),"
            + "    basicSalary INT NOT NULL,"
            + "    rentAllowance INT NOT NULL,"
            + "    transportationAllowance INT NOT NULL,"
            + "    medicalAllowance INT NOT NULL,"
            + "    mobileAllowance INT NOT NULL,"
            + "    healthInsurance INT NOT NULL,"
            + "    providentFund INT NOT NULL,"
            + "    incomeTax INT NOT NULL,"
            + "    lunchService INT NOT NULL);";


    public static void CreateTablesInDatabase() {
        DatabaseService databaseService = new DatabaseService();
        databaseService.executeUpdate(createTablesQuery);
    }



}
