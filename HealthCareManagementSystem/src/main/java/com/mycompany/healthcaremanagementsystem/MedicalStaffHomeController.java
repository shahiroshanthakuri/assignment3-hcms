/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
    }

    @FXML
    private void updatePatientEvent(ActionEvent event) {
    }

    @FXML
    private void logoutEvent(ActionEvent event) {
        App.switchScene("loginPage.fxml");
    }

    @FXML
    private void scheduleAppointmentEvent(ActionEvent event) {
    }

    @FXML
    private void viewBillsEvent(ActionEvent event) {
    }

    @FXML
    private void searchPatientEvent(ActionEvent event) {
    }

    @FXML
    private void viewAnalyticsDashboardEvent(ActionEvent event) {
    }

}
