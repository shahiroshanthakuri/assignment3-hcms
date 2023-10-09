
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
    public static String USER_TABLE_AUTO_INCREMENT = "ALTER TABLE User AUTO_INCREMENT = 101;";
    
    public static String SELECT_USER_BY_ID = "SELECT * FROM User WHERE UserID = ?";
    public static String SELECT_USER_BY_EMAIL = "SELECT * FROM User WHERE EmailAddress = ?";
    public static String UPDATE_USER_BY_ID = "UPDATE User SET firstname = ?, lastname = ?, gender = ? , dateofbirth = ?, emailaddress = ?, password = ?, role = ? WHERE UserId = ?";
    public static String DELETE_USER_BY_ID = "DELETE From User Where userid = ?";
    
    public static String INSERT_INTO_USER = "INSERT INTO User (FirstName, LastName, Gender, DateOfBirth, EmailAddress, Password, Role)\n" +
                                            "VALUES (?, ?, ?, ?, ?, ?, ?);";
    
            public static String PATIENT_TABLE = "CREATE TABLE Patient (\n" +
                                                    "    patient_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                                    "    firstname VARCHAR(255) NOT NULL,\n" +
                                                    "    lastname VARCHAR(255) NOT NULL,\n" +
                                                    "    gender VARCHAR(10),\n" +
                                                    "    dateOfBirth DATE,\n" +
                                                    "    address VARCHAR(255),\n" +
                                                    "    contactNum VARCHAR(20),\n" +
                                                    "    medicareNumber VARCHAR(20),\n" +
                                                    "    medicalHistory TEXT\n" +
                                                    ");";
            public static String INSERT_INTO_PATIENT = "INSERT INTO Patient (firstname, lastname, gender, dateOfBirth, address, contactNum, medicareNumber, medicalHistory)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static String PATIENT_TABLE_AUTO_INCREMENT = "ALTER TABLE Patient AUTO_INCREMENT = 1001;";
    public static String SEARCH_PATIENT_BY_ID = "SELECT * From Patient where patient_id = ?";
    public static String UPDATE_PATIENT_BY_ID = "UPDATE Patient SET firstname = ?, lastname = ?, gender = ?, dateOfBirth = ?, address = ?, contactNum = ?, medicareNumber = ?, medicalHistory = ? WHERE patient_id = ?";
    public static String DELETE_PATIENT_BY_ID = "DELETE FROM Patient WHERE patient_id = ?";
    
    
    public static String APPOINTMENT_TABLE = "CREATE TABLE Appointment (\n" +
                                                "    appointment_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                                "    appointment_date DATE NOT NULL,\n" +
                                                "    booked_service VARCHAR(100) NOT NULL,\n"
                                              + "    booking_time VARCHAR(20)," +
                                                "    patient_id INT,\n" +
                                                "    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)\n" +
                                                ");";
    public static String APPOINTMENT_TABLE_AUTO_INCREMENT = "ALTER TABLE Appointment AUTO_INCREMENT = 10001;";
    public static String INSERT_APPOINTMENT = "INSERT INTO Appointment (appointment_date, booked_service, booking_time, patient_id) VALUES (?, ?, ?, ?)";
    public static String SEARCH_APPOINTMENT_BYDATE_TIME = "SELECT * FROM Appointment WHERE booked_service = ? AND appointment_date = ? AND booking_time = ?";
    public static String DELETE_APPOINTMENT_BY_ID = "DELETE FROM Appointment Where appointment_id = ?";
    
    public static String INVOICE_TABLE = "CREATE TABLE Invoice (\n" +
                                        "    invoice_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                                        "    amount_due DECIMAL(10, 2) NOT NULL,\n" +
                                        "    invoice_date DATE NOT NULL,\n" +
                                        "    services_provided TEXT NOT NULL,\n" +
                                        "    appointment_id INT,\n" +
                                        "    patient_id INT,\n" +
                                        "    FOREIGN KEY (appointment_id) REFERENCES Appointment(appointment_id),\n" +
                                        "    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)\n" +
                                        ");";
    public static String INVOICE_TABLE_AUTO_INCREMENT = "ALTER TABLE Invoice AUTO_INCREMENT = 100001;";
    public static String INSERT_INVOICE = "INSERT INTO Invoice (amount_due, invoice_date, services_provided, appointment_id, patient_id) VALUES (?, ?, ?, ?, ?)";

}
