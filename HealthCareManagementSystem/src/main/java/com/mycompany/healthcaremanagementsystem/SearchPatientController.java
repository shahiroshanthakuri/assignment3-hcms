
package com.mycompany.healthcaremanagementsystem;

import Model.Patient;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author sahil
 */
public class SearchPatientController implements Initializable {


    @FXML
    private Text error;
    @FXML
    private TextField searchField;
    @FXML
    private Text foundUserText;
    @FXML
    private Button updateBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void logoutEvent(ActionEvent event) {
        App.switchScene("loginPage.fxml");
    }

    @FXML
    private void searchBtnEvent(ActionEvent event) {
        try {
            int patientID = Integer.parseInt(searchField.getText());
            
            // if user id is correct
            Patient p = App.getDb().selectPatientByID(patientID);
            if(p == null)
            {
                showError(true, "Patient with the given Patient - ID is not found");
              foundUserText.setVisible(false);
              updateBtn.setVisible(false);
            }
            else{
                showError(false, "");
              App.setSearchedPatient(p);
              foundUserText.setText("Found Patient: "+p.getFirstname()+" "+p.getLastname());
              foundUserText.setVisible(true);
              updateBtn.setVisible(true);

            }
            
        } catch (Exception e) {
            showError(true, "Please Enter the Correct Patient ID");
            foundUserText.setVisible(false);
              updateBtn.setVisible(false);
            
        }
    }

    @FXML
    private void updateUserEvent(ActionEvent event) {
        
    }

    @FXML
    private void homeBtn(ActionEvent event) {
        App.switchScene("medicalStaffHome.fxml");
    }
    
    public void showError(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

}
