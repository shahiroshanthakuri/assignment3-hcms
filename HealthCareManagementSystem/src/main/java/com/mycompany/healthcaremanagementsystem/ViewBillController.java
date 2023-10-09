
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author sahil
 */
public class ViewBillController implements Initializable {


    @FXML
    private Text patientNameLabel;
    @FXML
    private TextArea billArea;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void homeBtnEvent(ActionEvent event) {
        App.switchScene("medicalStaffHome.fxml");
    }

    @FXML
    private void logOutEvent(ActionEvent event) {
        App.switchScene("loginPage.fxml");
    }

  

    public TextArea getBillArea() {
        return billArea;
    }

    public Text getPatientNameLabel() {
        return patientNameLabel;
    }
    
    

}
