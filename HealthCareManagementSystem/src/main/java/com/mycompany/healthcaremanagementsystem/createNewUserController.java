
package com.mycompany.healthcaremanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void roleMenuBtn(ActionEvent event) {
        String role = roleMenu.getText();
        System.out.println("Role: "+role);
    }

    @FXML
    private void genderMenuBtn(ActionEvent event) {
    }

    @FXML
    private void createUserBtn(ActionEvent event) {
    }

}
