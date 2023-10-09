
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextArea;
/**
 * FXML Controller class
 *
 * @author sahil
 */
public class ViewAnalyticsController implements Initializable {


    @FXML
    private TextArea analyticsArea;
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

    public TextArea getAnalyticsArea() {
        return analyticsArea;
    }
    
    

}
