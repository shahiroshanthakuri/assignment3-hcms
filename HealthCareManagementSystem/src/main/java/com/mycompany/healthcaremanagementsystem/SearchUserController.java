
package com.mycompany.healthcaremanagementsystem;

import Model.User;
import java.io.IOException;
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
public class SearchUserController implements Initializable {


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
            int userID = Integer.parseInt(searchField.getText());
            
            // if user id is correct
            User u = App.getDb().selectUserByID(userID);
            if(u == null)
            {
                showError(true, "User with the given UserID is not found");
               foundUserText.setVisible(false);
              updateBtn.setVisible(false);
            }
            else{
              App.setSearchedUser(u);
              foundUserText.setText("Found User: "+u.getFirstName()+" "+u.getLastName());
              foundUserText.setVisible(true);
              updateBtn.setVisible(true);

            }
            
        } catch (Exception e) {
            showError(true, "Please Enter the Correct User ID");
            foundUserText.setVisible(false);
              updateBtn.setVisible(false);
            
        }
    }

    @FXML
    private void updateUserEvent(ActionEvent event) throws IOException {
        App.switchScene("modifyUser.fxml");
    }

    
        public void showError(boolean e,String msg)
    {
        error.setText(msg);
        error.setVisible(e);
    }

    @FXML
    private void homeBtn(ActionEvent event) {
        App.switchScene("adminHome.fxml");
    }

}
