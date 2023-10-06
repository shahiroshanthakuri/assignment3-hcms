
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;

public class Database {
    
    private String url;
    private String username;
    private String password;
    private Connection c;
    
    // create the database healthlink
    public Database(String username, String password)
    {
            String dbUrl = "jdbc:mysql://localhost:3306";

            // Establish a database connection
       try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
                    Statement s = connection.createStatement();
                    s.addBatch(Queries.DROP_DATABASE);
                    s.addBatch(Queries.CREATE_DATABASE);
                    s.executeBatch();
                    this.url = dbUrl+"/healthlink";
                    this.username = username;
                    this.password = password;

                    
                    s.close();
                }
         catch (Exception e) {
            e.printStackTrace();
        }
       createAllTables();
       setAutoIncrement();
       insertAdmin();
    }
    
    // secure a database connnection
    private Connection getConnection()
    {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            
        } catch (Exception e) {
        }
        return connection;
    }
    
    private void createAllTables()
    {
        try {
            
            Connection c = getConnection();
            Statement s = c.createStatement();
            String userT = Queries.USER_TABLE;
            String patientT = Queries.PATIENT_TABLE;
            String medicalStaffT = Queries.MEDICAL_STAFF_TABLE;
            String appointmentT = Queries.APPOINTMENT_TABLE;
            String billingT = Queries.BILLING_TABLE;
            
            s.addBatch(userT);
            s.addBatch(patientT);
            s.addBatch(medicalStaffT);
            s.addBatch(appointmentT);
            s.addBatch(billingT);
            
            s.executeBatch();
      
            
        } catch (Exception e) {
            System.out.println("Exception in create tables");
            e.printStackTrace();
        }
    }
    
    public void setAutoIncrement()
    {
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            s.addBatch(Queries.USER_TABLE_AUTO_INCREMENT);
            s.addBatch(Queries.PATIENT_TABLE_AUTO_INCREMENT);
            s.addBatch(Queries.MEDICAL_STAFF_TABLE_AUTO_INCREMENT);
            s.addBatch(Queries.BILLING_TABLE_AUTO_INCREMENT);
            s.addBatch(Queries.APPOINTMENT_TABLE_AUTO_INCREMENT);
            
            // execute all queries
            s.executeBatch();
            
        } catch (Exception e) {
            System.out.println("Exception in auto increment");
            e.printStackTrace();
        }
    }
    
    public void insertAdmin()
    {
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.INSERT_INTO_USER);
            LocalDate date = LocalDate.now();
            
            User admin = new User(-1,"admin", "admin", "male", java.sql.Date.valueOf(date), "admin@mail.com", "admin", "admin");
            
            ps.setString(1,admin.getFirstName());
            ps.setString(2,admin.getLastName());
            ps.setString(3,admin.getGender());
            ps.setDate(4,admin.getDateOfBirth());
            ps.setString(5,admin.getEmail());
            ps.setString(6,admin.getPassword());
            ps.setString(7,admin.getRole());
            
            ps.executeUpdate();
            ps.close();
            
                     
            
        } catch (Exception e) {
            System.out.println("Exception in admin");
            e.printStackTrace();
        }
    }
    
}
