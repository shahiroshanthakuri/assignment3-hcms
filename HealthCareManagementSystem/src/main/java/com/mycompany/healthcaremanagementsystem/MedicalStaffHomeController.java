
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author sahil
 */
public class MedicalStaffHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void addPatientEvent(ActionEvent event) {
        App.switchScene("addNewPatient.fxml");
    }

    @FXML
    private void updatePatientEvent(ActionEvent event) {
        App.switchScene("searchPatient.fxml");
    }

    @FXML
    private void logoutEvent(ActionEvent event) {
        App.switchScene("loginPage.fxml");
    }

    @FXML
    private void scheduleAppointmentEvent(ActionEvent event) {
        App.switchScene("bookAppointment.fxml");
    }

    @FXML
    private void viewBillsEvent(ActionEvent event) {
        App.switchScene("searchPatientBill.fxml");
    }

    @FXML
    private void searchPatientEvent(ActionEvent event) {
    }

    @FXML
    private void viewAnalyticsDashboardEvent(ActionEvent event) {
    }

}
