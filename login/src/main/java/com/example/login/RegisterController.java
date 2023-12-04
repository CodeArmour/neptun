package com.example.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.paint.Color;


public class RegisterController {

    @FXML
    private Button closeBtn;
    @FXML
    private Label registrationMessege;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private PasswordField confirmTxt;
    @FXML
    private Label confirmationMessege;
    @FXML
    private TextField firstNameTxt;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private TextField userNameTxt;
    @FXML
    private Label usernameMessege;
    @FXML
    private TextField subjectTxt;




    public void closeBtnOnAction(ActionEvent event){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();

    }

    private boolean isUsernameTaken(String username) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String query = "SELECT * FROM account_user WHERE user_name = '" + username + "'";

        try {
            Statement statement = connectionDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet.next(); // If resultSet.next() is true, it means the username already exists.

        } catch (Exception e) {
            System.out.println(e);
            return true; // Handle exceptions appropriately in your actual implementation
        }
    }

    public void registerBtnOnAction(ActionEvent event){
        if (passwordTxt.getText().equals(confirmTxt.getText())) {
            confirmationMessege.setText(" ");
            if (!isUsernameTaken(userNameTxt.getText())) {
                usernameMessege.setText(" ");
                if (!isEmptyField(firstNameTxt) && !isEmptyField(lastNameTxt) && !isEmptyField(userNameTxt)
                        && !isEmptyField(passwordTxt) && !isEmptyField(confirmTxt)&& !isEmptyField(subjectTxt)) {
                    usernameMessege.setText(" ");
                    confirmationMessege.setText(" ");

                    Stage currentStage = (Stage) closeBtn.getScene().getWindow();
                    currentStage.close();

                    registerUser(); // Proceed with registration
                }else {
                    showErrorMessage("All fields must be filled!");
                }
            } else {
                usernameMessege.setText("Username already taken. Please choose another one.");
                usernameMessege.setTextFill(Color.RED);
            }
        }  else {
            confirmationMessege.setText("No connection !");
            confirmationMessege.setTextFill(Color.RED);
        }

    }



    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String firstname = firstNameTxt.getText();
        String lastname = lastNameTxt.getText();
        String username = userNameTxt.getText();
        String password = passwordTxt.getText();
        String subject = subjectTxt.getText();

        String insertFields = "insert into account_user(first_name,last_name,user_name,password,subject) values('";
        String insertValues = firstname +"','"+ lastname +"','"+ username +"','"+ password +"','"+ subject +"')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationMessege.setText("User registered successfully !!!!");

            // Clear the text fields after successful registration
            firstNameTxt.setText("");
            lastNameTxt.setText("");
            userNameTxt.setText("");
            passwordTxt.setText("");
            confirmTxt.setText("");
            subjectTxt.setText("");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private boolean isEmptyField(TextField textField) {
        return textField.getText().trim().isEmpty();
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
