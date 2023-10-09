
package com.mycompany.healthcaremanagementsystem;

import Model.Appointment;
import Model.Invoice;
import Model.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author sahil
 */
public class BookAppointmentController implements Initializable {


    @FXML
    private TextField searchField;
    @FXML
    private Text foundUser;
    @FXML
    private DatePicker bookingDateField;
    @FXML
    private Button bookAppointmentBtn;
    @FXML
    private Text error;
    @FXML
    private MenuButton bookingReasonMenu;
    @FXML
    private MenuButton bookingTimeMenu;
    
    private boolean isPatientFound = false;
    @FXML
    private Label bookingForLabel;
    @FXML
    private Label bookingDateLabel;
    @FXML
    private Label bookingTimeLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void searchBtn(ActionEvent event) {
        try {
            int patientID = Integer.parseInt(searchField.getText());
            
            // if user id is correct
            Patient p = App.getDb().selectPatientByID(patientID);
            if(p == null)
            {
              showError(true, "Patient with the given Patient - ID is not found");
              foundUser.setVisible(false);
              isPatientFound = false;
            }
            else{
               showError(false, "");
              App.setSearchedPatient(p);
              foundUser.setText("Book for the Patient: "+p.getFirstname()+" "+p.getLastname());
              foundUser.setVisible(true);
              isPatientFound = true;
                showAppointmentFields(true);

            }
            
        } catch (Exception e) {
            showError(true, "Please Enter the Correct Patient ID");
            foundUser.setVisible(false);
            isPatientFound = false;
        }
    }

    @FXML
    private void bookingMenuEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        bookingReasonMenu.setText(selected);
    }

    @FXML
    private void bookAppointmentEvent(ActionEvent event) {
        if(isPatientFound)
        {
            String bookingReason = bookingReasonMenu.getText();
            java.sql.Date bookingDate= null;
            String bookingTime = bookingTimeMenu.getText();
            
            if(bookingReason.isBlank() || bookingReason.equalsIgnoreCase("Select Reason"))
            {
                showError(true, "Please Choose a Booking reason");
            }
            else if(bookingDateField.getValue() == null)
            {
                showError(true, "Booking date is required");
            }
            else if(bookingTime.isBlank() || bookingTime.equalsIgnoreCase("Select Time"))
            {
                showError(true, "Please Choose Booking Time");
            }
            else{
                showError(false, "");
                bookingDate = java.sql.Date.valueOf(bookingDateField.getValue());
                
                Appointment a = new Appointment(-1, bookingDate, bookingReason, bookingTime, App.getSearchedPatient().getPatientId());
                
                if(App.getDb().searchForExistingAppointment(a))
                {
                    showError(true, "The Selected Appointment Slot is already booked please select another date or time.");
                }
                else{
                    showError(false, "");

                    a = App.getDb().addAppointment(a);

                    // Display a success alert
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Health Care Management System");
                    alert.setHeaderText("Success");
                    alert.setContentText("Appointment has been booked for the Patient: "+App.getSearchedPatient().getFirstname()+"\n\nBooking Date: "+a.getAppointmentDate()+" "+a.getBookingTime());
                    alert.getButtonTypes().setAll(ButtonType.OK);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {

                        }
                    });

                    clearFields();
                    
                    // automatic generate the invoice
                    Invoice i = new Invoice(-1, bookingDate, bookingReason, a.getAppointmentID(), a.getPatientID());
                    i = App.getDb().addInvoice(i);
               }

            }
        }
        else{
            showError(true, "Please search the patient first");
        }
    }
    
    public void showError(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

    @FXML
    private void bookingTimeEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        bookingTimeMenu.setText(selected);
    }
    
        public void clearFields()
    {
        searchField.setText("");
        foundUser.setVisible(false);
        bookingDateField.setValue(null);
        bookingReasonMenu.setText("Select Reason");
        bookingTimeMenu.setText("Select Time");
        showAppointmentFields(false);
        showError(false, "");
    }
        
    public void showAppointmentFields(boolean show)
    {
        bookingDateField.setVisible(show);
        bookingReasonMenu.setVisible(show);
        bookingTimeMenu.setVisible(show);
        bookAppointmentBtn.setVisible(show);
        bookingDateLabel.setVisible(show);
        bookingForLabel.setVisible(show);
        bookingTimeLabel.setVisible(show);
    }

    @FXML
    private void homeBtn(ActionEvent event) {
        App.switchScene("medicalStaffHome.fxml");
    }

    @FXML
    private void logOutBtn(ActionEvent event) {
        App.switchScene("loginPage.fxml");
    }


}
