module com.mycompany.healthcaremanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.healthcaremanagementsystem to javafx.fxml;
    exports com.mycompany.healthcaremanagementsystem;
}
