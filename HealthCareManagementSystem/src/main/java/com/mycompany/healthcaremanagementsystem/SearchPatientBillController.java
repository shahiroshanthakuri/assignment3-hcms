
package com.mycompany.healthcaremanagementsystem;

import Model.Invoice;
import Model.Patient;
import java.net.URL;
import java.util.List;
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
public class SearchPatientBillController implements Initializable {


    @FXML
    private TextField searchField;
    @FXML
    private Text error;
    @FXML
    private Text foundUserText;
    @FXML
    private Button viewBillBtn;
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
              viewBillBtn.setVisible(false);

            }
            else{
                showError(false, "");
              App.setSearchedPatient(p);
              foundUserText.setText("Found Patient: "+p.getFirstname()+" "+p.getLastname());
              foundUserText.setVisible(true);
              viewBillBtn.setVisible(true);

            }
            
        } catch (Exception e) {
            showError(true, "Please Enter the Correct Patient ID");
            foundUserText.setVisible(false);
            viewBillBtn.setVisible(false);

        }
        
    }

    @FXML
    private void viewBillEvent(ActionEvent event) {
        
        List<Invoice> li = App.getDb().getAllInvoicesByID(App.getSearchedPatient());
        if(li.size() == 0)
        {
            showError(true, "The Selected Patient has no bills to show");
        }
        else{
            
        App.switchScene("viewBill.fxml");
        }
        
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
