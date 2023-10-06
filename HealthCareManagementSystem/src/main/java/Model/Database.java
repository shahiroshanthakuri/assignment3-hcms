
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
    
}
