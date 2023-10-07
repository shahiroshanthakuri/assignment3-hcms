
package Model;
import com.mycompany.healthcaremanagementsystem.App;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Database {
    
    private String url;
    private String username;
    private String password;
    private Connection c;
    private App app;
    
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
       app = new App();
       createAllTables();
       setAutoIncrement();
       LocalDate date = LocalDate.now();
            
       User admin = new User(-1,"admin", "admin", "male", java.sql.Date.valueOf(date), "admin@mail.com", "admin", "admin");
       insertAdmin(admin);
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
    
    public User insertAdmin(User admin)
    {
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.INSERT_INTO_USER);
            
            
            ps.setString(1,admin.getFirstName());
            ps.setString(2,admin.getLastName());
            ps.setString(3,admin.getGender());
            ps.setDate(4,admin.getDateOfBirth());
            ps.setString(5,admin.getEmail());
            ps.setString(6,admin.getPassword());
            ps.setString(7,admin.getRole());
            
            int ra = ps.executeUpdate();
            Statement s = c.createStatement();

            if (ra > 0) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    long id = rs.getInt(1);
                    admin.setId(id);
                }
            }
            // setting the current user
            app.setCurrentUser(admin);
            ps.close();
            
                     
            
        } catch (Exception e) {
            System.out.println("Exception in admin");
            e.printStackTrace();
        }
        return admin;
    }
    
    
    public User selectUserByID(long id)
    {
        User u = null;
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.SELECT_USER_BY_ID);
            ps.setLong(1, id);
            
            ResultSet rs= ps.executeQuery();
            if(rs.next())
            {
                u = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            else{
                return u;
            }
            
        } catch (Exception e) {
            System.out.println("Exception in getting user by id");
            e.printStackTrace();
        }
            
        return u;
    }
 
   
    
}
