module com.example.filechoosertxt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filechoosertxt to javafx.fxml;
    exports com.example.filechoosertxt;
}