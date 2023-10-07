
package com.mycompany.healthcaremanagementsystem;

import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author sahil
 */
public class createNewUserController implements Initializable {

    @FXML
    private MenuButton roleMenu;
    @FXML
    private MenuButton genderMenu;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private TextField confirmPassField;
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
    private void createUserBtn(ActionEvent event) {
        String firstName = firstnameField.getText();
        String lastName = lastnameField.getText();
        String gender = genderMenu.getText();
        java.sql.Date dateOfBirth= null;
        String email = emailField.getText();
        String password = passwordField.getText();
        String confPass = confirmPassField.getText();
        String role = roleMenu.getText();
        
        if(firstName.isBlank())
        {
            showError(true, "Firstname is required!");
        }
        else if(lastName.isBlank())
        {
            showError(true, "LastName is required!");
        }
        else if(gender.isBlank() || gender.compareTo("Select Gender") == 0)
        {
            showError(true, "Please select a gender");
        }
        else if(dateOfBirthField.getValue() == null)
        {
            showError(true, "Please select date of birth");
        }
        else if(email.isBlank())
        {
            showError(true, "Email Address is required");
        }
        else if(password.isBlank())
        {
            showError(true, "Password is required");
        }
        else if(confPass.isBlank())
        {
            showError(true, "Confirm Password is required");
        }
        else if(role.isBlank() || role.compareTo("Select Role") == 0)
        {
            showError(true, "Please select a role");
        }
        else if(!password.equalsIgnoreCase(confPass))
        {
            showError(true, "Both the passwords must match");
        }
        else{
            showError(false, "");
            // add the user to the database
            dateOfBirth = java.sql.Date.valueOf(dateOfBirthField.getValue());
            User user = App.getDb().insertAdmin(new User(-1, firstName, lastName, gender, dateOfBirth, email, password, role));
            
            
            // show the success message
                        // Display a success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Health Care Management System");
            alert.setHeaderText("Success");
            alert.setContentText("A New User has been Added\n\nUserID: "+user.getId()+"\n\nImportant: Please keep this user id for login in the system.");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });
        }
    }

    @FXML
    private void genderMenuEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        genderMenu.setText(selected);
    }

    @FXML
    private void roleMenuEvent(ActionEvent event) {
                MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        roleMenu.setText(selected);
    }
    
        
    public void showError(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

}
