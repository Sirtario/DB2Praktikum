package Kiss.controller;

import Kiss.model.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private AnchorPane ViewBase;
    @FXML
    private TextField UserNameInput;
    @FXML
    private PasswordField PasswordInput;

    @FXML
    private void onLoginButtonClick()
    {
        //check if username and password are filled in
        if(!CredentialsAreFilledIn())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Please enter your Credentials", ButtonType.OK);
            alert.setTitle("Credentials Required!");
            alert.show();
            return;
        }
        //create database connection with the credentials
        if(ConnectToDB(UserNameInput.getText(), PasswordInput.getText()))
        {
            try {
                LoadMainView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void LoadMainView() throws IOException {
        Stage stage = (Stage) ViewBase.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("../../view/main.fxml"));
        stage.setTitle("KIS");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    private boolean CredentialsAreFilledIn() {
        if (UserNameInput.getText()==null||UserNameInput.getText().trim().isEmpty())
        {
            return false;
        }
        if (PasswordInput.getText()==null|| PasswordInput.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }

    private Boolean ConnectToDB(String user, String pass) {

        try{
            DatabaseConnector.ConnectToDB(UserNameInput.getText(), PasswordInput.getText());
            return true;
        } catch (SQLException e) {
            Alert dbAlert = new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK);
            dbAlert.show();
            return false;
        }
    }
}
