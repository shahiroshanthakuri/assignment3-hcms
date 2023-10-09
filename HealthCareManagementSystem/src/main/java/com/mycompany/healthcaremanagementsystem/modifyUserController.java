
package com.mycompany.healthcaremanagementsystem;

import Model.User;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author sahil
 */
public class modifyUserController implements Initializable {


    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPassField;
    @FXML
    private Text error;
    @FXML
    private TextField genderField;
    @FXML
    private TextField dateOfBirthField;
    @FXML
    private TextField roleField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void deleteUserEvent(ActionEvent event) {
        
        System.out.println("Current User: "+App.getCurrentUser().getFirstName());
        System.out.println("Searched User: "+App.getSearchedUser().getFirstName());

        if(App.getCurrentUser().getId() == App.getSearchedUser().getId())
        {
             displayMessage("Failed", "You are currently logged in. \n\nCan not delete your account.");
        }
        else{
            App.getDb().deleteUserById(App.getSearchedUser().getId());
            displayMessage("Success", "The User Deleted Successfully.\n\nRedirecting to Search Page.");
            App.switchScene("searchUser.fxml");
        }
    }

    @FXML
    private void adminHomeEvent(ActionEvent event) {
        
            App.switchScene("adminHome.fxml");
       
    }



    @FXML
    private void updateUserBtn(ActionEvent event) {
        String firstName = firstnameField.getText();
        String lastName = lastnameField.getText();
        String gender = genderField.getText();
        String dateOfBirth= dateOfBirthField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confPass = confirmPassField.getText();
            String role = roleField.getText();
        if(firstName.isBlank())
        {
            showError(true, "FirstName can not be blank");
        }
        else if(lastName.isBlank())
        {
            showError(true, "LastName can not be blank");
        }
        else if(gender.isBlank())
        {
            showError(true, "Gender can not be blank");
        }
        else if(dateOfBirth.isBlank())
        {
            showError(true, "Date of birth can not be blank");
        }
        else if(email.isBlank())
        {
            showError(true, "Email can not be blank");
        }
        else if(password.isBlank())
        {
            showError(true, "Password can not be blank");
        }
        else if(confPass.isBlank())
        {
            showError(true, "Confirm Password can not be blank");
        }
        else if(role.isBlank())
        {
            showError(true, "Role can not be blank");
        }else{
            boolean dobCorrect = false;
            java.sql.Date sqlDateOfBirth = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                  java.util.Date utilDate = dateFormat.parse(dateOfBirth);
                        sqlDateOfBirth = new java.sql.Date(utilDate.getTime());
                        dobCorrect = true;
            } catch (Exception e) {
                       showError(true, "Date Of Birth format is incorrect. Please use 'YYYY-MM-DD' Format");
            }
            if(dobCorrect)
            {
                if(!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("medical staff"))
                {
                    showError(true, "Role can be either Admin or Medical Staff");
                }
                else{
                    User u = new User(App.getSearchedUser().getId(), firstName, lastName, gender, sqlDateOfBirth, email, password, role);
                    u = App.getDb().updateUserByID(u);
                    if(u == null)
                    {
                        displayMessage("Failed", "Failed to Update the User");
                    }
                    else{
                        displayMessage("Success", "The User with Name: "+u.getFirstName()+" "+u.getLastName()+" has been updated");
                    }
                }      
            }
        }
        
    }
    
        public TextField getFirstnameField() {
        return firstnameField;
    }

    public TextField getLastnameField() {
        return lastnameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getConfirmPassField() {
        return confirmPassField;
    }

    public TextField getGenderField() {
        return genderField;
    }

    public TextField getDateOfBirthField() {
        return dateOfBirthField;
    }

    public TextField getRoleField() {
        return roleField;
    }
    
        public void showError(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }
        
    public void displayMessage(String header, String content)
    {
            // Display a success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Health Care Management System");
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });
    }
    


    
    

}
