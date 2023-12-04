module com.example.rft {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rft to javafx.fxml;
    exports com.example.rft;
}