
package com.mycompany.healthcaremanagementsystem;

import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String userID = emailField.getText();
        String password = passwordField.getText();
        
        if(userID.isBlank())
        {
            showError(true, "UserID is required!");
        }
        else if(password.isBlank())
        {
            showError(true, "Password is required!");
        }
        else{
            boolean valid = false;
            long uID = -1;
            try {
                uID = Long.parseLong(userID);
                valid = true;
            } catch (Exception e) {
                showError(true, "Please Enter a Valid User ID");
            }
            if(valid)
            {
                User u = App.getDb().selectUserByID(uID);
                if(u == null)
                {
                    showError(true, "User with the given id does not exist");
                }
                else{
                    if(u.getPassword().compareTo(password) == 0)
                    {
                        showError(false, "");
                        if(u.getRole().equalsIgnoreCase("admin"))
                        {
                           
                                App.switchScene("adminHome.fxml");
                                App.setCurrentUser(u);
                        
                        }
                        else if(u.getRole().equalsIgnoreCase("medical staff"))
                        {
                            App.switchScene("medicalStaffHome.fxml");
                            App.setCurrentUser(u);
                        }

                    }
                    else{
                        showError(true, "Password is Incorrect");
                    }

                }
                
            }
            

   
        }
        
    }
    
    public void showError(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

}
