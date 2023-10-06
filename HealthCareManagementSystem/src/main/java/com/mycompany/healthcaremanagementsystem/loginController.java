
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author sahil
 */
public class loginController implements Initializable {


    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
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
    private void loginEvent(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        
        if(email.isBlank())
        {
            showError(true, "Email Address is required!");
        }
        else if(password.isBlank())
        {
            showError(true, "Password is required!");
        }
        else{
            showError(false, "");
            
        }
        
    }
    
    public void showError(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

}
