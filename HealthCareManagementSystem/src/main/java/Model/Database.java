
package Model;
import com.mycompany.healthcaremanagementsystem.App;
import com.mycompany.healthcaremanagementsystem.UpdatePatientController;
import com.mycompany.healthcaremanagementsystem.ViewAnalyticsController;
import com.mycompany.healthcaremanagementsystem.ViewBillController;
import com.mycompany.healthcaremanagementsystem.modifyUserController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
       LocalDate date = LocalDate.now();
        
       User admin = new User(-1,"admin", "admin", "male", java.sql.Date.valueOf(date), "admin@mail.com", "a", "Admin");
       User medicalStaff = new User(-1,"Medical", "Staff", "male", java.sql.Date.valueOf(date), "medicalStaff@mail.com", "a", "Medical Staff");
       Patient p = new Patient(-1, "Patient", "Patient", "Male", java.sql.Date.valueOf(date), "123 main Street", "123 049 0599", "9867 87362 4", "None");
       insertAdmin(admin);
       insertAdmin(medicalStaff);
        addNewPatient(p);
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
            String appointmentT = Queries.APPOINTMENT_TABLE;
            String billingT = Queries.INVOICE_TABLE;
            
            s.addBatch(userT);
            s.addBatch(patientT);
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
            s.addBatch(Queries.INVOICE_TABLE_AUTO_INCREMENT);
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
            
            ps.close();
            
                     
            
        } catch (Exception e) {
            System.out.println("Exception in admin");
            e.printStackTrace();
        }
        return admin;
    }
    
    
    public User updateUserByID(User admin)
    {
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.UPDATE_USER_BY_ID);
            
            
            ps.setString(1,admin.getFirstName());
            ps.setString(2,admin.getLastName());
            ps.setString(3,admin.getGender());
            ps.setDate(4,admin.getDateOfBirth());
            ps.setString(5,admin.getEmail());
            ps.setString(6,admin.getPassword());
            ps.setString(7,admin.getRole());
            ps.setLong(8, admin.getId());
            
            ps.executeUpdate();
            ps.close();
            
                     
            
        } catch (Exception e) {
            System.out.println("Exception in update user");
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
    
    public void deleteUserById(long id)
    {
        
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.DELETE_USER_BY_ID);
            ps.setLong(1, id);
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Exception in Deleting user by id");
            e.printStackTrace();
        }
            
    }
    
    
    public Patient addNewPatient(Patient p)
    {
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.INSERT_INTO_PATIENT);
            
            
            ps.setString(1,p.getFirstname());
            ps.setString(2,p.getLastname());
            ps.setString(3,p.getGender());
            ps.setDate(4,p.getDateOfBirth());
            ps.setString(5,p.getAddress());
            ps.setString(6,p.getContactNum());
            ps.setString(7,p.getMedicareNumber());
            ps.setString(8,p.getMedicalHistory());

            
            int ra = ps.executeUpdate();
            Statement s = c.createStatement();

            if (ra > 0) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    long id = rs.getInt(1);
                    p.setPatientId(id);
                }
            }
            // setting the current user
            
            ps.close();
            
                     
            
        } catch (Exception e) {
            System.out.println("Exception in add new patient");
            e.printStackTrace();
        }
        return p;
        
    }
    
    public Patient selectPatientByID(long id)
    {
        Patient p = null;
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.SEARCH_PATIENT_BY_ID);
            ps.setLong(1, id);
            
            ResultSet rs= ps.executeQuery();
            if(rs.next())
            {
                p = new Patient(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
            else{
                return p;
            }
            
        } catch (Exception e) {
            System.out.println("Exception in getting Patient by id");
            e.printStackTrace();
        }
            
        return p;
    }
    
    
   public Patient updatePatientByID(Patient p)
    {
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.UPDATE_PATIENT_BY_ID);
            
            
            ps.setString(1,p.getFirstname());
            ps.setString(2,p.getLastname());
            ps.setString(3,p.getGender());
            ps.setDate(4,p.getDateOfBirth());
            ps.setString(5,p.getAddress());
            ps.setString(6,p.getContactNum());
            ps.setString(7,p.getMedicareNumber());
            ps.setString(8, p.getMedicalHistory());
            ps.setLong(9, p.getPatientId());
            
            ps.executeUpdate();
            ps.close();
            
                     
            
        } catch (Exception e) {
            System.out.println("Exception in update Patient");
            e.printStackTrace();
        }
        return p;
    }
   
   public Appointment addAppointment(Appointment a)
    {
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.INSERT_APPOINTMENT);
            
            
            ps.setDate(1,a.getAppointmentDate());
            ps.setString(2,a.getBookingService());
            ps.setString(3,a.getBookingTime());
            ps.setLong(4,a.getPatientID());


            int ra = ps.executeUpdate();
            Statement s = c.createStatement();

            if (ra > 0) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    long id = rs.getInt(1);
                    a.setAppointmentID(id);
                }
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            System.out.println("Exception in add new Appointment");
            e.printStackTrace();
        }
        return a;
        
    }
   
   
    public boolean searchForExistingAppointment(Appointment a)
    {
        boolean ans = false;
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.SEARCH_APPOINTMENT_BYDATE_TIME);
            
            
            ps.setString(1,a.getBookingService());
            ps.setDate(2,a.getAppointmentDate());
            ps.setString(3,a.getBookingTime());


            ResultSet rs = ps.executeQuery();
           

            ans =  rs.next();
           ps.close();
  
        } catch (Exception e) {
            System.out.println("Exception in search existing Appointment");
            e.printStackTrace();
        }
        return ans;
        
    }
    
    public Invoice addInvoice(Invoice i)
    {
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.INSERT_INVOICE);
            
            
            ps.setDouble(1,i.getAmountDue());
            ps.setDate(2,i.getInvoiceDate());
            ps.setString(3,i.getServiceProvided());
            ps.setLong(4,i.getAppointmentID());
            ps.setLong(5,i.getPatientID());


            int ra = ps.executeUpdate();
            Statement s = c.createStatement();

            if (ra > 0) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    long id = rs.getInt(1);
                    i.setInvoiceID(id);
                }
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            System.out.println("Exception in add new Invoice");
            e.printStackTrace();
        }
        return i;
        
    }
    
    
    public void modifyUserSetFields(modifyUserController muc, User u)
    {
        muc.getFirstnameField().setText(u.getFirstName());
        muc.getLastnameField().setText(u.getLastName());
        muc.getGenderField().setText(u.getGender());
        muc.getDateOfBirthField().setText(u.getDateOfBirth().toString());
        muc.getEmailField().setText(u.getEmail());
        muc.getPasswordField().setText(u.getPassword());
        muc.getConfirmPassField().setText(u.getPassword());
        muc.getRoleField().setText(u.getRole());
    }
    
    public void updatePatientSetFields(UpdatePatientController upc, Patient p)
    {
        upc.getFirstnameField().setText(p.getFirstname());
        upc.getLastnameField().setText(p.getLastname());
        upc.getGenderMenu().setText(p.getGender());
        upc.getDateOfBirthField().setValue(p.getDateOfBirth().toLocalDate());
        upc.getAddressField().setText(p.getAddress());
        upc.getContactNumberField().setText(p.getContactNum());
        upc.getMedicareCardField().setText(p.getMedicareNumber());
        upc.getMedicalHistoryField().setText(p.getMedicalHistory());
    }
    
    public List<Invoice> getAllInvoicesByID(Patient p)
    {
        List<Invoice> list = new ArrayList<>();
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.SELECT_ALL_INVOICE_BY_ID);
            
            
            ps.setLong(1,p.getPatientId());


            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                Invoice i = new Invoice(rs.getLong(1), rs.getDate(3), rs.getString(4), rs.getLong(5), rs.getLong(6));
                list.add(i);
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            System.out.println("Exception in getInvoiceByID");
            e.printStackTrace();
        }
        return list;
    }
    
        public List<Invoice> getAllInvoices()
    {
        List<Invoice> list = new ArrayList<>();
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.SELECT_ALL_INVOICE);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                Invoice i = new Invoice(rs.getLong(1), rs.getDate(3), rs.getString(4), rs.getLong(5), rs.getLong(6));
                list.add(i);
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            System.out.println("Exception in getAllInvoice");
            e.printStackTrace();
        }
        return list;
    }
        
    public List<Patient> getAllPatients()
    {
        List<Patient> list = new ArrayList<>();
        try {
            
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(Queries.SELECT_ALL_PATIENTS);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
              Patient  p = new Patient(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));

                list.add(p);
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            System.out.println("Exception in getAllPatients");
            e.printStackTrace();
        }
        return list;
    }
    
    
        
        
    public void setBillView(ViewBillController vbc, Patient p)
    {
        vbc.getPatientNameLabel().setText(p.getFirstname()+" "+p.getLastname());
        List<Invoice> li = getAllInvoicesByID(p);
        vbc.getBillArea().appendText("\n");
        String billView = "";
        double grandTotal = 0.0;
        for(Invoice i:li)
        {
            billView += "Invoice ID: "+i.getInvoiceID()+"\n" +
                        "------------------------------------------------------\n" +
                        "Appointment Date: "+i.getInvoiceDate()+"\n" +
                        "Appointment ID: "+i.getAppointmentID()+"\n" +
                        "Service Selected: "+i.getServiceProvided()+"\n" +
                        "Charges: $ "+i.getAmountDue()+"\n" +
                        "GST: $ "+i.getGST()+"\n" +
                        "------------------------------------------------------\n" +
                        "Total: $ "+i.getTotal()+"\n\n";
            grandTotal += Double.parseDouble(i.getTotal());
        }
        vbc.getBillArea().appendText(billView);
        vbc.getBillArea().appendText("\n");
        vbc.getBillArea().appendText("------------------------------------------------------\n"+""
                + "Grand Total: $ "+grandTotal);

        
    }
    
    public void setAnalyticsView(ViewAnalyticsController vac)
    {
        // All Patients
        List<Patient> patientList = getAllPatients();
        List<Invoice> invoiceList = getAllInvoices();
        
        int totalPatient = patientList.size();
        int totalAppointments = invoiceList.size();
        double totalRevenue = 0;
        double totalGST = 0;
        double averageIncome = 0;
        double grossTotal = 0;
        
        int regularCheckupNo = 0;
        int intensiveCareNo = 0;
        int surgeryNo = 0;
        int doctorConsultationNo = 0;
        
        double regularCheckup = 0;
        double intensiveCare = 0;
        double surgery = 0;
        double doctorConsultation = 0;
        
        
        for(Invoice i:invoiceList)
        {
            totalRevenue += i.getAmountDue();
            totalGST += Double.parseDouble(i.getGST());
            grossTotal += Double.parseDouble(i.getTotal());
            if(i.getServiceProvided().equalsIgnoreCase("Regular Checkup"))
            {
                regularCheckupNo++;
                regularCheckup += i.getAmountDue();
            }
            else if(i.getServiceProvided().equalsIgnoreCase("Intensive Care"))
            {
                intensiveCareNo++;
                intensiveCare += i.getAmountDue();
            }
            else if(i.getServiceProvided().equalsIgnoreCase("Surgery "))
            {
                surgeryNo++;
                surgery += i.getAmountDue();
            }
            else if(i.getServiceProvided().equalsIgnoreCase("Doctor Consultation"))
            {
                doctorConsultationNo++;
                doctorConsultation += i.getAmountDue();
            }
        }
        
        averageIncome = totalRevenue/totalPatient;
        
        LocalDate now = LocalDate.now();
        vac.getAnalyticsArea().appendText("							HealthLink Hospital				Today Date: "+now.toString()+"\n" +
"----------------------------------------------------------------------------------------------------------------------\n" +
"\n" +
"							\n" +
"\n" +
"-----------------------------------------------\n" +
"\n" +
"               Overall Revenue\n" +
"\n" +
"-----------------------------------------------\n" +
"\n" +
"Total Patients : "+totalPatient+"\n" +
"\n" +
"Total Appointements : "+totalAppointments+"\n" +
"\n" +
"Total Revenue Generated : $ "+totalRevenue+" (exl GST)\n" +
"\n" +
"Average Income Per Patient : $ "+averageIncome+ " (exl GST)\n" +
"\n" +
"Total GST Colleted : $ "+totalGST+"\n" +
"\n" +
"Gross  Total : $ "+grossTotal+" (including GST)\n" +
"\n" +
"------------------------------------------------\n" +
"\n" +
"           HealthCare Services Trends\n" +
"\n" +
"------------------------------------------------\n" +
"\n" +
"Regular Checkups : "+regularCheckupNo+"\n" +
"\n" +
"Intensive Care : "+intensiveCareNo+"\n" +
"\n" +
"Surgery : "+surgeryNo+"\n" +
"\n" +
"Doctor Consultation : "+doctorConsultationNo+"\n" +
"\n" +
"------------------------------------------------\n" +
"\n" +
"          Income Generated By Services\n" +
"\n" +
"------------------------------------------------\n" +
"\n" +
"Regular Checkups : $ "+regularCheckup+" (exl GST)\n" +
"\n" +
"Intensive Care : $ "+intensiveCare+" (exl GST)\n" +
"\n" +
"Surgery : $ "+surgery+" (exl GST)\n" +
"\n" +
"Doctor Consultation : $ "+doctorConsultation+" (exl GST)\n" +
"\n" +
"Total Income : $ "+grossTotal+" (including GST)\n" +
"\n" +
"----------------------------------------------------------------------------------------------------------------------\n" +
"\n" +
"                                   Thank You For Using Health Care Management System");
        
    }
 
   
    
}
