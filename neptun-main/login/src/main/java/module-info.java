module com.example.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.login to javafx.fxml;
    exports com.example.login;

    requires java.sql;
    requires java.desktop;

    //requires mysql.connector.java;


}