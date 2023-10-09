
package Model;

import java.sql.Date;

/**
 *
 * @author sahil
 */
public class Appointment {
    private long appointmentID;
    private java.sql.Date appointmentDate;
    private String bookingService;
    private String bookingTime;
    private long patientID;

    public Appointment(long appointmentID, java.sql.Date appointmentDate, String bookingService, String bookingTime, long patientID) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.bookingService = bookingService;
        this.bookingTime = bookingTime;
        this.patientID = patientID;
    }

    public long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public java.sql.Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(java.sql.Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getBookingService() {
        return bookingService;
    }

    public void setBookingService(String bookingService) {
        this.bookingService = bookingService;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }
    
    
    
    
}
