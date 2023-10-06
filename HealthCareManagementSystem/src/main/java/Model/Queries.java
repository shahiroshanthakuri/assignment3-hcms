
package Model;

/**
 *
 * @author sahil
 */
public class Queries {
    
    public static String DROP_DATABASE = "DROP DATABASE IF EXISTS healthlink;";
    public static String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS healthlink;";
    
    public static String USER_TABLE = "CREATE TABLE User (\n" +
                                        "    UserID INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                        "    FirstName VARCHAR(50) NOT NULL,\n" +
                                        "    LastName VARCHAR(50) NOT NULL,\n" +
                                        "    Gender VARCHAR(20),\n" +
                                        "    DateOfBirth DATE,\n" +
                                        "    EmailAddress VARCHAR(100),\n" +
                                        "    Password VARCHAR(255) NOT NULL,\n" +
                                        "    Role VARCHAR(20) NOT NULL\n" +
                                        ");";
    public static String USER_TABLE_AUTO_INCREMENT = "ALTER TABLE User AUTO_INCREMENT = 1000;";
    
            public static String PATIENT_TABLE = "CREATE TABLE Patient (\n" +
                "    patientid INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    FirstName VARCHAR(50) NOT NULL,\n" +
                "    LastName VARCHAR(50) NOT NULL,\n" +
                "    Gender VARCHAR(20),\n" +
                "    DateOfBirth DATE,\n" +
                "    EmailAddress VARCHAR(100),\n" +
                "    Password VARCHAR(255) NOT NULL,\n" +
                "    MedicalHistory TEXT,\n" +
                "    Allergies TEXT\n" +  // Remove the extra comma here
                ");";


    
    public static String MEDICAL_STAFF_TABLE = "CREATE TABLE MedicalStaff (\n" +
                                                "    StaffID INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                                "    FirstName VARCHAR(50) NOT NULL,\n" +
                                                "    LastName VARCHAR(50) NOT NULL,\n" +
                                                "    Gender VARCHAR(20),\n" +
                                                "    DateOfBirth DATE,\n" +
                                                "    EmailAddress VARCHAR(100),\n" +
                                                "    Password VARCHAR(255) NOT NULL,\n" +
                                                "    Specialty VARCHAR(50),\n" +
                                                "    WorkingSchedule TEXT\n" +
                                                ");";
    
    public static String APPOINTMENT_TABLE = "CREATE TABLE Appointment (\n" +
                                            "    AppointmentID INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                            "    PatientID INT,\n" +
                                            "    HealthcareProviderID INT,\n" +
                                            "    AppointmentDate DATE,\n" +
                                            "    AppointmentTime TIME,\n" +
                                            "    Purpose TEXT,\n" +
                                            "    Status VARCHAR(20) DEFAULT 'Scheduled',\n" +
                                            "    FOREIGN KEY (PatientID) REFERENCES Patient(patientid),\n" +
                                            "    FOREIGN KEY (HealthcareProviderID) REFERENCES MedicalStaff(StaffID)\n" +
                                            ");";
    
    public static String BILLING_TABLE = "CREATE TABLE BillingInvoice (\n" +
                                        "    InvoiceID INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                        "    PatientID INT,\n" +
                                        "    InvoiceDate DATE,\n" +
                                        "    DueDate DATE,\n" +
                                        "    TotalAmount DECIMAL(10, 2),\n" +
                                        "    PaymentStatus VARCHAR(20) DEFAULT 'Unpaid',\n" +
                                        "    PaymentMethod VARCHAR(50),\n" +
                                        "    FOREIGN KEY (PatientID) REFERENCES Patient(PatientID)\n" +
                                        ");";
}
