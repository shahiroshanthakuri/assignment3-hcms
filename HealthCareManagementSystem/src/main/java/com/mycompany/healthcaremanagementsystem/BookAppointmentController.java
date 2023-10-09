
package com.mycompany.healthcaremanagementsystem;

import Model.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
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
    private MenuButton bookingMenu;
    @FXML
    private DatePicker bookingDateField;
    @FXML
    private Button bookAppointmentBtn;
    @FXML
    private Text error;
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
            }
            else{
               showError(false, "");
              App.setSearchedPatient(p);
              foundUser.setText("Found Patient: "+p.getFirstname()+" "+p.getLastname());
              foundUser.setVisible(true);

            }
            
        } catch (Exception e) {
            showError(true, "Please Enter the Correct Patient ID");
            foundUser.setVisible(false);
            
        }
    }

    @FXML
    private void bookingMenuEvent(ActionEvent event) {
    }

    @FXML
    private void bookAppointmentEvent(ActionEvent event) {
    }
    
    public void showError(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }


}
