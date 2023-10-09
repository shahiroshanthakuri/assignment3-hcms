
package Model;

/**
 *
 * @author sahil
 */
public class Patient {
    private long patientId;
    private String firstname;
    private String lastname;
    private String address;
    private String contactNum;
    private String medicareNumber;
    private String medicalHistory;

    public Patient(long patientId, String firstname, String lastname, String address, String contactNum, String medicareNumber, String medicalHistory) {
        this.patientId = patientId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.contactNum = contactNum;
        this.medicareNumber = medicareNumber;
        this.medicalHistory = medicalHistory;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getMedicareNumber() {
        return medicareNumber;
    }

    public void setMedicareNumber(String medicareNumber) {
        this.medicareNumber = medicareNumber;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
    
    
    
    
}
