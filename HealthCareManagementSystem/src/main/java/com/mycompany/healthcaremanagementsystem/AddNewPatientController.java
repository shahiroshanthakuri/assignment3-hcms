
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author sahil
 */
public class AddNewPatientController implements Initializable {


    @FXML
    private MenuButton genderMenu;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private TextField medicareCardField;
    @FXML
    private Text error;
    @FXML
    private TextArea medicalHistoryField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void genderMenuEvent(ActionEvent event) {
        
    }

    @FXML
    private void addPatientBtn(ActionEvent event) {
        
    }

    @FXML
    private void homeBtn(ActionEvent event) {
        App.switchScene("medicalStaffHome.fxml");
    }

    @FXML
    private void logoutBtn(ActionEvent event) {
        App.switchScene("loginPage.fxml");
    }

}
