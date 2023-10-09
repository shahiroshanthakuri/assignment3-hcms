package com.mycompany.healthcaremanagementsystem;

import Model.Database;
import Model.Patient;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage primaryStage; // Main application stage
    private static Scene currentScene; // Current scene displayed
    private static Database db;
    private static User currentUser;
    private static User searchedUser;
    private static Patient searchedPatient;
    public static boolean isDatabaseExist = false;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("HealthCare Management System");
        switchScene("loginPage.fxml");
        primaryStage.show();
    }
    // Method to switch between different scenes in the application
    public static void switchScene(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlFileName));
            Parent root = loader.load();

            // Database setup (if not already created)
            if (!isDatabaseExist) {
                db = new Database("root", "P@55W0Rd1995");
                isDatabaseExist = true;
            }

            if(fxmlFileName.equalsIgnoreCase("modifyUser.fxml"))
            {
                modifyUserController muc = (modifyUserController) loader.getController();
                getDb().modifyUserSetFields(muc, getSearchedUser());
            }
            if(fxmlFileName.equalsIgnoreCase("updatePatient.fxml"))
            {
                UpdatePatientController upc = (UpdatePatientController) loader.getController();
                getDb().updatePatientSetFields(upc, getSearchedPatient());
            }
            if(fxmlFileName.equalsIgnoreCase("viewBill.fxml"))
            {
                ViewBillController vbc = (ViewBillController) loader.getController();
                getDb().setBillView(vbc, getSearchedPatient());
            }
            if(fxmlFileName.equalsIgnoreCase("viewAnalytics.fxml")){
                ViewAnalyticsController vac = (ViewAnalyticsController) loader.getController();
                getDb().setAnalyticsView(vac);
            }
            
            // Set the current scene to the new scene
            currentScene = new Scene(root);
            primaryStage.setScene(currentScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static Database getDb() {
        return db;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static User getSearchedUser() {
        return searchedUser;
    }

    public static void setSearchedUser(User searchedUser) {
        App.searchedUser = searchedUser;
    }

    public static Patient getSearchedPatient() {
        return searchedPatient;
    }

    public static void setSearchedPatient(Patient searchedPatient) {
        App.searchedPatient = searchedPatient;
    }
 
    
}