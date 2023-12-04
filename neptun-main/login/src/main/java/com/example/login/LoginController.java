package com.example.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    //variables for btns and labels i will use ...
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessege;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField passwordTextfield;
    @FXML
    private Button loginButton;


    //fun I will use to make the events when I click ...
    public void loginButtonOnAction(ActionEvent event){

        if (usernameTextfield.getText().isBlank() == false && passwordTextfield.getText().isBlank()== false){
            vaildataLogin();
        }else {
            loginMessege.setText("Enter username and password");
        }

    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage =(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerMainBtnOnAction(ActionEvent event){
        creatRegistrationForm();
    }


    public void vaildataLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from account_user where user_name= '"+usernameTextfield.getText()+"' and password='"+passwordTextfield.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1)==1){
                    loginMessege.setText("Connect!!");
                    loginMessege.setTextFill(Color.GREEN);

                    Stage currentStage = (Stage) cancelButton.getScene().getWindow();
                    currentStage.close();

                    showDash();


                }else {
                    loginMessege.setText("Invalid Login");
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void creatRegistrationForm(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();


        }catch (Exception e){
            System.out.println(e);

        }
    }

    public void showDash(){
        try{
            loginButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Scene scene = null;
            scene = new Scene(loader.load());
            Stage menuStage=new Stage();
            menuStage.initStyle(StageStyle.UNDECORATED);
            menuStage.setScene(scene);
            menuStage.show();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}