
package Model;

import java.sql.Date;

/**
 *
 * @author sahil
 */
public class Invoice {
    private long invoiceID;
    private double amountDue;
    private java.sql.Date invoiceDate;
    private String serviceProvided;
    private long appointmentID;
    private long patientID;
    private final double REGULAR_CHECKUP = 200.0;
    private final double INTENSIVE_CARE = 4000.0;
    private final double SURGERY = 12000.0;
    private final double DOCTOR_CONSULTATION = 140.0;


    public Invoice(long invoiceID, java.sql.Date invoiceDate, String serviceProvided, long appointmentID, long patientID) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.serviceProvided = serviceProvided;
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        determinePrice();
    }

    public long getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(long invoiceID) {
        this.invoiceID = invoiceID;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
    
    private void determinePrice()
    {
        if(getServiceProvided().equalsIgnoreCase("Regular Checkup"))
        {
            setAmountDue(REGULAR_CHECKUP);
        }
        else if(getServiceProvided().equalsIgnoreCase("Intensive Care"))
        {
            setAmountDue(INTENSIVE_CARE);
        }
        else if(getServiceProvided().equalsIgnoreCase("Surgery "))
        {
            setAmountDue(SURGERY);
        }
        else if(getServiceProvided().equalsIgnoreCase("Doctor Consultation"))
        {
            setAmountDue(DOCTOR_CONSULTATION);
        }
        else{
            setAmountDue(100.0);
        }
    }

    public java.sql.Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(java.sql.Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getServiceProvided() {
        return serviceProvided;
    }

    public void setServiceProvided(String serviceProvided) {
        this.serviceProvided = serviceProvided;
    }

    public long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }
    
    public String getGST()
    {
        double gst = (0.1 * getAmountDue());
        return String.format("%.2f", gst);
    }
    
    public String getTotal()
    {
        double total = ((0.1) * getAmountDue())+ getAmountDue();
        return String.format("%.2f", total);
    }
    
}
